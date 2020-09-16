package com.tampro.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.CallCardDAO;
import com.tampro.dto.Paging;
import com.tampro.entity.CallCard;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CallCardDAOImpl extends BaseDAOImpl<CallCard> implements CallCardDAO<CallCard> {
	
	public int saveInt(CallCard instance) {
		// TODO Auto-generated method stub
		return (Integer) factory.getCurrentSession().save(instance);
	}

	public List<CallCard> findAllUnfinish(String queryStr, Map<String, Object> mapParams, Paging paging) {
		System.out.println("====== find All Unfinish ======");
		StringBuilder query = new StringBuilder("");
		StringBuilder count = new StringBuilder("");
		query.append(" from ").append(getGenericName().toString()).append(" as model where model.activeFlag = 1  and model.status = 0 ");
		count.append(" select count(*) from ").append(getGenericName().toString()).append(" as model where model.activeFlag = 1 and model.status = 0 ");
		if(!queryStr.isEmpty()) {
			query.append(queryStr);
			count.append(queryStr);
		}
		Query countQuery  = factory.getCurrentSession().createQuery(count.toString());
		Query queryFind  = factory.getCurrentSession().createQuery(query.toString());
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

	public List<CallCard> findAllFinish(String queryStr, Map<String, Object> mapParams, Paging paging) {
		System.out.println("====== find All Finish======");
		StringBuilder query = new StringBuilder("");
		StringBuilder count = new StringBuilder("");
		query.append(" from ").append(getGenericName().toString()).append(" as model where model.activeFlag = 1 and model.status = 1 ");
		count.append(" select count(*) from ").append(getGenericName().toString()).append(" as model where model.activeFlag = 1 and model.status = 1 ");
		if(!queryStr.isEmpty()) {
			query.append(queryStr);
			count.append(queryStr);
		}
		Query countQuery  = factory.getCurrentSession().createQuery(count.toString());
		Query queryFind  = factory.getCurrentSession().createQuery(query.toString());
		System.out.println("Query : "+ query.toString());
		if(mapParams !=null&&!mapParams.isEmpty()) {
			for(String key : mapParams.keySet()) {
				countQuery.setParameter(key,mapParams.get(key));
				queryFind.setParameter(key,mapParams.get(key));
				System.out.println(mapParams.get(key));
			}
		}

		if(paging != null) {
			long totalRows =   (Long) countQuery.uniqueResult();
			paging.setTotalRows(totalRows);
			queryFind.setFirstResult(paging.getOffSet());
			queryFind.setMaxResults(paging.getRecordPerPage());
		}
		
		
		return queryFind.getResultList();
	}
}
