package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.PublisherDAO;
import com.tampro.entity.Publisher;

@Repository
@Transactional(rollbackFor = Exception.class)
public class PublisherDAOImpl extends BaseDAOImpl<Publisher> implements PublisherDAO<Publisher> {

}
