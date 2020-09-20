package com.tampro.service;

import java.util.List;

import com.tampro.dto.MenuDTO;
import com.tampro.dto.Paging;

public interface MenuService {
	MenuDTO findById(int id);
	List<MenuDTO> findByProperty(String property, Object object);
	List<MenuDTO> findAll(MenuDTO menuDTO,Paging paging);
	void update(MenuDTO menuDTO) throws Exception;
	List<MenuDTO> findAlls2(MenuDTO menuDTO,Paging paging);
	void changeStatus(int id);

}
