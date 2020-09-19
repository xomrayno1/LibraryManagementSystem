package com.tampro.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.AuthDAO;
import com.tampro.entity.Auth;
@Repository
@Transactional(rollbackFor = Exception.class)
public class AuthDAOImpl  extends BaseDAOImpl<Auth> implements AuthDAO<Auth>{

	@Override
	public Auth find(int roleId, int menuId) {
		// TODO Auto-generated method stub
		Query query = factory.getCurrentSession().createQuery(" FROM Auth where menu.id =:menuId and role.id =:roleId ");
		query.setParameter("menuId", menuId);
		query.setParameter("roleId", roleId);
		if(!query.getResultList().isEmpty()) {
			return (Auth) query.getResultList().get(0);
		}
		return null;
	}

}
