package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.UsersDAO;
import com.tampro.dto.Paging;
import com.tampro.dto.RoleDTO;
import com.tampro.dto.UserRolesDTO;
import com.tampro.dto.UsersDTO;
import com.tampro.entity.Users;
import com.tampro.service.UserRoleService;
import com.tampro.service.UserService;
import com.tampro.utils.ConvertToDTO;

@Service
public class UserServiceImpl  implements UserService{
	@Autowired
	UsersDAO<Users> usersDAO;
	@Autowired
	UserRoleService userRoleService;
	public List<UsersDTO> findByProperty(String property, Object object) {
		List<UsersDTO> usersDTO =  new ArrayList<UsersDTO>();
		for(Users users : usersDAO.findByProperty(property, object)) {
			UsersDTO dto =  ConvertToDTO.convertUsersEntityToDTO(users);
			usersDTO.add(dto);
		}
		return usersDTO;
	}

	public UsersDTO findById(int id) {
		// TODO Auto-generated method stub
		Users users = usersDAO.findById(Users.class, id);
		UsersDTO usersDTO = ConvertToDTO.convertUsersEntityToDTO(users);
		return usersDTO;
	}

	public void save(UsersDTO usersDTO) throws Exception {
		// TODO Auto-generated method stub
		Users users = new Users();
		users.setActiveFlag(1);
		users.setCmnd(usersDTO.getCmnd());
		users.setCreateDate(new Date());
		users.setFullName(usersDTO.getFullName());
		users.setPassword(usersDTO.getPassword());
		users.setSdt(usersDTO.getSdt());
		users.setUpdateDate(new Date());
		users.setUsername(usersDTO.getUsername());
		int id = usersDAO.saveInt(users);
		UserRolesDTO userRolesDTO = new UserRolesDTO();
		userRolesDTO.setRoleDTO(new RoleDTO(usersDTO.getIdRoles()));
		userRolesDTO.setIdUsers(id);
		userRoleService.save(userRolesDTO);
	}

	public void update(UsersDTO usersDTO) throws Exception {
		// TODO Auto-generated method stub
		Users users = new Users();
		users.setActiveFlag(usersDTO.getActiveFlag());
		users.setCmnd(usersDTO.getCmnd());
		users.setCreateDate(usersDTO.getCreateDate());
		users.setFullName(usersDTO.getFullName());
		users.setPassword(usersDTO.getPassword());
		users.setSdt(usersDTO.getSdt());
		users.setUpdateDate(new Date());
		users.setId(usersDTO.getId());
		users.setUsername(usersDTO.getUsername());
		usersDAO.update(users);
		List<UserRolesDTO> userRolesDTOs = userRoleService.findByProperty("users.id", usersDTO.getId());
		if(!userRolesDTOs.isEmpty()) {
			userRolesDTOs.get(0).setRoleDTO(new RoleDTO(usersDTO.getIdRoles()));
			userRoleService.update(userRolesDTOs.get(0));
		}
	}

	public void delete(UsersDTO usersDTO) throws Exception {
		// TODO Auto-generated method stub
		Users users = new Users();
		users.setActiveFlag(0);
		users.setCmnd(usersDTO.getCmnd());
		users.setCreateDate(usersDTO.getCreateDate());
		users.setFullName(usersDTO.getFullName());
		users.setPassword(usersDTO.getPassword());
		users.setSdt(usersDTO.getSdt());
		users.setUpdateDate(new Date());
		users.setUsername(usersDTO.getUsername());
		users.setId(usersDTO.getId());
		usersDAO.delete(users);
		List<UserRolesDTO> userRolesDTOs = userRoleService.findByProperty("users.id", usersDTO.getId());
		if(!userRolesDTOs.isEmpty()) {
			userRoleService.delete(userRolesDTOs.get(0));
		}
	}

	public List<UsersDTO> findAll(UsersDTO usersDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		if(usersDTO != null) {
			if(usersDTO.getFullName() != null && !StringUtils.isEmpty(usersDTO.getFullName())) {
				queryStr.append(" and model.fullName like :fullName ");
				mapParams.put("fullName","%"+usersDTO.getFullName()+"%");
			}
		}
		List<UsersDTO> usersDTOs =  new ArrayList<UsersDTO>();
		for(Users users : usersDAO.findAll(queryStr.toString(), mapParams, paging)) {
			UsersDTO dto =  ConvertToDTO.convertUsersEntityToDTO(users);
			usersDTOs.add(dto);
		}
		return usersDTOs;
	}

	

}
