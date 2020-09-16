package com.tampro.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.BaseDAO;
import com.tampro.dto.Paging;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BaseDAOImpl<E>  implements BaseDAO<E>{

	@Autowired
	SessionFactory factory;

	public List<E> findAll(String queryStr, Map<String, Object> mapParams, Paging paging) {
		// TODO Auto-generated method stub
		System.out.println("====== find All ======");
		StringBuilder query = new StringBuilder("");
		StringBuilder count = new StringBuilder("");
		query.append(" from ").append(getGenericName().toString()).append(" as model where model.activeFlag = 1 ");
		count.append(" select count(*) from ").append(getGenericName().toString()).append(" as model where model.activeFlag = 1");
		if(!queryStr.isEmpty()) {
			query.append(queryStr);
			count.append(queryStr);
		}
		Query<E> countQuery  = factory.getCurrentSession().createQuery(count.toString());
		Query<E> queryFind  = factory.getCurrentSession().createQuery(query.toString());
		System.out.println("Query : "+ query.toString());
		if(mapParams !=null&&!mapParams.isEmpty()) {
			for(String key : mapParams.keySet()) {
				countQuery.setParameter(key,mapParams.get(key));
				queryFind.setParameter(key,mapParams.get(key));
				System.out.println(mapParams.get(key));
			}
		}

		if(paging != null) {
			long totalRows =  (Long) countQuery.uniqueResult();
			paging.setTotalRows(totalRows);
			queryFind.setFirstResult(paging.getOffSet());
			queryFind.setMaxResults(paging.getRecordPerPage());
		}
		
		
		return queryFind.getResultList();
	}

	public List<E> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		System.out.println("====== find by property ======");
		StringBuilder queryStr = new StringBuilder("");
		queryStr.append(" from ").append(getGenericName().toString())
		.append(" as model  where  model.activeFlag = 1 ");
		String param = property;
		if(property != null ) {	
			if(!returnStr(param)) {
				String[] str = property.split("\\.");
				param  = str[0]+str[1]; //truong hop property co dau cham => hibernet loi DotNode
													//=> thay param cho truong hop co dau cham
			}
			queryStr.append(" and model."+property).append(" =:"+param);
		}
		
		System.out.println("Query : " + queryStr.toString());
		Query<E> query = factory.getCurrentSession().createQuery(queryStr.toString());
		if(object != null) {
			query.setParameter(param, object);
		}
 		return query.getResultList();
	}
	public boolean returnStr(String property) { //truong hop property co dau cham
		String[] str = property.split("\\.");
		if(str.length > 1) {
			return false;
		}
		return true;
	}

	public E findById(Class<E> e, Serializable id) {
		// TODO Auto-generated method stub
		return factory.getCurrentSession().find(e,id);
	}

	public void save(E instance) {
		// TODO Auto-generated method stub
		System.out.println("====== Save ======");
		factory.getCurrentSession().persist(instance);
	}

	public void update(E instance) {
		// TODO Auto-generated method stub
		System.out.println("====== update ======");
		factory.getCurrentSession().merge(instance);
	}

	public void delete(E instance) {
		// TODO Auto-generated method stub
		System.out.println("====== delete ======");
		factory.getCurrentSession().merge(instance);
	}
	public String getGenericName() { // Trả về class hiện tại đag sử dụng
		String s = getClass().getGenericSuperclass().toString();
		Pattern pattern = Pattern.compile("\\<(.*?)\\>"); // tạo pattern
		Matcher m = pattern.matcher(s); // check xem s có hợp lệ hay k , trả về Matcher
		String generic="null"; 
		if(m.find()) { // kiểm tra 
			generic = m.group(1);
		}
		return generic;
	}

	public long countByProperty(String property, Object object) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(*) from ").append(getGenericName()).append(" as model where model.activeFlag = 1 ");
		if(property!=null && object != null) {
			queryStr.append(" and model."+property).append(" =:"+property);
		}
		Query<E> query = factory.getCurrentSession().createQuery(queryStr.toString());
		if( property!=null && object !=null) {
			query.setParameter(property, object);
		}
		long count = (Long) query.uniqueResult();
		return count;
	}


}
