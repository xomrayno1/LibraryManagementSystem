package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.ReadersDAO;
import com.tampro.entity.Readers;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ReadersDAOImpl extends BaseDAOImpl<Readers> implements ReadersDAO<Readers> {

}
