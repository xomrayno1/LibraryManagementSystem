package com.tampro.dao;

import java.util.List;
import java.util.Map;

import com.tampro.dto.Paging;
import com.tampro.entity.Menu;

public interface MenuDAO<E>  extends BaseDAO<E>{
	
	List<Menu> findAlls2(String queryStr,Map<String,Object> mapParams, Paging paging );

}
