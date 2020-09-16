package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.MenuDAO;
import com.tampro.entity.Menu;
@Repository
@Transactional(rollbackFor = Exception.class)
public class MenuDAOImpl  extends BaseDAOImpl<Menu> implements MenuDAO<Menu>{

}
