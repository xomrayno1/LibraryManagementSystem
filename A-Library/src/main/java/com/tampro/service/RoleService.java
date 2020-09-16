package com.tampro.service;

import java.util.List;

import com.tampro.dto.Paging;
import com.tampro.dto.RoleDTO;

public interface RoleService {
	RoleDTO findById(int id);
	List<RoleDTO> findByProperty(String property, Object object);
	List<RoleDTO> findAll(RoleDTO roleDTO,Paging paging);
	void save(RoleDTO roleDTO) throws Exception;
	void update(RoleDTO roleDTO) throws Exception;
	void delete(RoleDTO roleDTO) throws Exception;
}
