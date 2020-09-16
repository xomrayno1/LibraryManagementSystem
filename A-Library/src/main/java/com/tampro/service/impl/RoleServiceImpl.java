package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.RoleDAO;
import com.tampro.dto.Paging;
import com.tampro.dto.RoleDTO;
import com.tampro.entity.Role;
import com.tampro.service.RoleService;
import com.tampro.utils.ConvertToDTO;

@Service
public class RoleServiceImpl  implements RoleService{

	@Autowired
	RoleDAO<Role> roleDAO;
	
	public RoleDTO findById(int id) {
		// TODO Auto-generated method stub
		Role role = roleDAO.findById(Role.class, id);
		RoleDTO roleDTO = ConvertToDTO.convertRoleEntityToDTO(role);
		return roleDTO;
	}

	public List<RoleDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		for(Role role : roleDAO.findByProperty(property, object)) {
			RoleDTO dto = ConvertToDTO.convertRoleEntityToDTO(role);
			roleDTOs.add(dto);
		}
		return roleDTOs;
	}

	public List<RoleDTO> findAll(RoleDTO roleDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		for(Role role : roleDAO.findAll(queryStr.toString(), mapParams, paging)) {
			RoleDTO dto = ConvertToDTO.convertRoleEntityToDTO(role);
			roleDTOs.add(dto);
		}
		return roleDTOs;
	}

	public void save(RoleDTO roleDTO) throws Exception {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setActiveFlag(1);
		role.setCreateDate(new Date());
		role.setDescription(roleDTO.getDescription());
		role.setName(roleDTO.getName());
		role.setUpdateDate(new Date());
		roleDAO.save(role);
	}

	public void update(RoleDTO roleDTO) throws Exception {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setActiveFlag(roleDTO.getActiveFlag());
		role.setCreateDate(roleDTO.getCreateDate());
		role.setDescription(roleDTO.getDescription());
		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName());
		role.setUpdateDate(new Date());
		roleDAO.update(role);
	}

	public void delete(RoleDTO roleDTO) throws Exception {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setActiveFlag(0);
		role.setCreateDate(roleDTO.getCreateDate());
		role.setDescription(roleDTO.getDescription());
		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName());
		role.setUpdateDate(new Date());
		roleDAO.delete(role);
	}

}
