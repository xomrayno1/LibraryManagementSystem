package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.UserRolesDAO;
import com.tampro.entity.UserRoles;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UserRolesDAOImpl  extends BaseDAOImpl<UserRoles> implements UserRolesDAO<UserRoles>{

}
