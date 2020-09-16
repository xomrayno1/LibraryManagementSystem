package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.UserRolesDAO;
import com.tampro.dto.Paging;
import com.tampro.dto.UserRolesDTO;
import com.tampro.entity.Role;
import com.tampro.entity.UserRoles;
import com.tampro.entity.Users;
import com.tampro.service.UserRoleService;
import com.tampro.utils.ConvertToDTO;

@Service
public class UserRoleServiceImpl  implements UserRoleService{
	@Autowired
	UserRolesDAO<UserRoles> userRolesDAO;

	public List<UserRolesDTO> findAll(UserRolesDTO userRolesDTO, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<UserRolesDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<UserRolesDTO> userRolesDTOs  = new ArrayList<UserRolesDTO>();
		for(UserRoles userRoles : userRolesDAO.findByProperty(property, object)) {
			UserRolesDTO userRolesDTO = ConvertToDTO.convertUserRolesEntityToDTO(userRoles);
			userRolesDTOs.add(userRolesDTO);
		}
		
		return userRolesDTOs;
	}

	public UserRolesDTO findById(int id) {
		// TODO Auto-generated method stub
		UserRoles userRoles = userRolesDAO.findById(UserRoles.class, id);
		UserRolesDTO userRolesDTO = ConvertToDTO.convertUserRolesEntityToDTO(userRoles);
		return userRolesDTO;
	}

	public void save(UserRolesDTO userRolesDTO) throws Exception {
		// TODO Auto-generated method stub
		UserRoles userRoles = new UserRoles();
		userRoles.setActiveFlag(1);
		userRoles.setCreateDate(new Date());
		userRoles.setRole(new Role(userRolesDTO.getRoleDTO().getId()));
		userRoles.setUpdateDate(new Date());
		userRoles.setUsers(new Users(userRolesDTO.getIdUsers()));
		userRolesDAO.save(userRoles);
	}

	public void update(UserRolesDTO userRolesDTO) throws Exception {
		// TODO Auto-generated method stub
		UserRoles userRoles = new UserRoles();
		userRoles.setActiveFlag(userRolesDTO.getActiveFlag());
		userRoles.setCreateDate(userRolesDTO.getCreateDate());
		userRoles.setId(userRolesDTO.getId());
		userRoles.setRole(new Role(userRolesDTO.getRoleDTO().getId()));
		userRoles.setUpdateDate(new Date());
		userRoles.setUsers(new Users(userRolesDTO.getIdUsers()));
		userRolesDAO.update(userRoles);
	}

	public void delete(UserRolesDTO userRolesDTO) throws Exception {
		// TODO Auto-generated method stub
		UserRoles userRoles = new UserRoles();
		userRoles.setActiveFlag(0);
		userRoles.setCreateDate(userRolesDTO.getCreateDate());
		userRoles.setId(userRolesDTO.getId());
		userRoles.setRole(new Role(userRolesDTO.getRoleDTO().getId()));
		userRoles.setUpdateDate(new Date());
		userRoles.setUsers(new Users(userRolesDTO.getIdUsers()));
		userRolesDAO.delete(userRoles);
	}

}
