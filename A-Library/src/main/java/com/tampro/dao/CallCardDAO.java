package com.tampro.dao;

import java.util.List;
import java.util.Map;

import com.tampro.dto.Paging;

public interface CallCardDAO<E> extends BaseDAO<E> {
	int saveInt(E instance);
	List<E> findAllUnfinish(String queryStr,Map<String,Object> mapParams, Paging paging );
	List<E> findAllFinish(String queryStr,Map<String,Object> mapParams, Paging paging );
}
