package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.AuthDAO;
import com.tampro.entity.Auth;
@Repository
@Transactional(rollbackFor = Exception.class)
public class AuthDAOImpl  extends BaseDAOImpl<Auth> implements AuthDAO<Auth>{

}
