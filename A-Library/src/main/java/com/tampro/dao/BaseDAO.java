package com.tampro.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tampro.dto.Paging;

public interface BaseDAO<E>{
	List<E> findAll(String queryStr,Map<String,Object> mapParams, Paging paging );
	List<E> findByProperty(String property , Object object);
	E findById(Class<E> e, Serializable id);
	long countByProperty(String property  , Object object);
	void save(E instance);
	void update(E instance);
	void delete(E instance);

}
