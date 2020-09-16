package com.tampro.dao;

import java.util.List;
import java.util.Map;

import com.tampro.dto.Paging;

public interface CallCardDetailDAO<E> extends BaseDAO<E> {

	List<E> findAllFinish(String queryStr,Map<String,Object> mapParams, Paging paging );
}
