package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.MenuDAO;
import com.tampro.dto.MenuDTO;
import com.tampro.dto.Paging;
import com.tampro.entity.Menu;
import com.tampro.service.MenuService;
import com.tampro.utils.ConvertToDTO;

@Service
public class MenuServiceImpl  implements MenuService{
	
	@Autowired
	MenuDAO<Menu> menuDAO;

	public MenuDTO findById(int id) {
		// TODO Auto-generated method stub
		Menu menu = menuDAO.findById(Menu.class, id);
		MenuDTO menuDTO = ConvertToDTO.convertMenuEntityToDTO(menu);
		return menuDTO;
	}

	public List<MenuDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
		for(Menu menu : menuDAO.findByProperty(property, object)) {
			MenuDTO dto = ConvertToDTO.convertMenuEntityToDTO(menu);
			menuDTOs.add(dto);
		}		
		return menuDTOs;
	}

	public List<MenuDTO> findAlls2(MenuDTO menuDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		if(menuDTO != null) {
			if(!StringUtils.isEmpty(menuDTO.getUrl()) && menuDTO.getUrl() != null) {
				queryStr.append(" and model.url like :url ");
				mapParams.put("url","%"+menuDTO.getUrl()+"%");
			}
		}
		List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
		for(Menu menu : menuDAO.findAlls2(queryStr.toString(), mapParams, paging)) {
			MenuDTO dto = ConvertToDTO.convertMenuEntityToDTO(menu);
			menuDTOs.add(dto);
		}		
		return menuDTOs;
	}

	public void update(MenuDTO menuDTO) throws Exception {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		menu.setActiveFlag(menuDTO.getActiveFlag());
		menu.setCreateDate(menuDTO.getCreateDate());
		menu.setId(menuDTO.getId());
		menu.setName(menuDTO.getName());
		menu.setOrderIndex(menuDTO.getOrderIndex());
		menu.setParentId(menuDTO.getParentId());
		menu.setUpdateDate(new Date());
		menu.setUrl(menuDTO.getUrl());
		menuDAO.update(menu);
	}

	@Override
	public void changeStatus(int id) {
		// TODO Auto-generated method stub
		MenuDTO menuDTO = findById(id);
		menuDTO.setActiveFlag(menuDTO.getActiveFlag() == 1 ? 0 : 1);
		try {
			update(menuDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<MenuDTO> findAll(MenuDTO menuDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		if(menuDTO != null) {
			if(!StringUtils.isEmpty(menuDTO.getUrl()) && menuDTO.getUrl() != null) {
				queryStr.append(" and model.url =:url ");
				mapParams.put("url","%"+menuDTO.getUrl()+"%");
			}
		}
		List<MenuDTO> menuDTOs = new ArrayList<MenuDTO>();
		for(Menu menu : menuDAO.findAll(queryStr.toString(), mapParams, paging)) {
			MenuDTO dto = ConvertToDTO.convertMenuEntityToDTO(menu);
			menuDTOs.add(dto);
		}		
		return menuDTOs;
	}

}
