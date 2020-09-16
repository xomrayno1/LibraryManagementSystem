package com.tampro.service;

import java.util.List;

import com.tampro.dto.Paging;
import com.tampro.dto.UserRolesDTO;

public interface UserRoleService {

	List<UserRolesDTO> findAll(UserRolesDTO userRolesDTO,Paging paging);
	public List<UserRolesDTO> findByProperty(String property , Object object);
	UserRolesDTO findById(int id);
	void save(UserRolesDTO userRolesDTO) throws Exception;
	void update(UserRolesDTO userRolesDTO) throws Exception;
	void delete(UserRolesDTO userRolesDTO) throws Exception;
}
