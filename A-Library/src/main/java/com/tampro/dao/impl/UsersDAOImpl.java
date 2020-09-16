package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.UsersDAO;
import com.tampro.entity.Users;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UsersDAOImpl  extends BaseDAOImpl<Users> implements UsersDAO<Users>{

	public int saveInt(Users users) {
		// TODO Auto-generated method stub
		return (Integer) factory.getCurrentSession().save(users);
	}

}
