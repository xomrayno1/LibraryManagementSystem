package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.AuthDAO;
import com.tampro.dto.AuthDTO;
import com.tampro.dto.Paging;
import com.tampro.entity.Auth;
import com.tampro.entity.Menu;
import com.tampro.entity.Role;
import com.tampro.service.AuthService;
import com.tampro.utils.ConvertToDTO;

@Service
public class AuthServiceImpl  implements AuthService{

	@Autowired
	AuthDAO<Auth> authDAO;
	
	@Override
	public AuthDTO findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<AuthDTO> list = new ArrayList<AuthDTO>();
		for(Auth auth : authDAO.findByProperty(property, object)) {
			AuthDTO authDTO = ConvertToDTO.convertAuthEntityToDTO(auth);
			list.add(authDTO);
		}
		return list;
	}

	@Override
	public List<AuthDTO> findAll(AuthDTO authDTO, Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AuthDTO authDTO) throws Exception {
		// TODO Auto-generated method stub
		Auth auth = new Auth();
		auth.setActiveFlag(authDTO.getActiveFlag());
		auth.setCreateDate(authDTO.getCreateDate());
		auth.setId(authDTO.getId());
		auth.setMenu(new Menu(authDTO.getMenuDTO().getId()));
		auth.setPermission(authDTO.getPermission());
		auth.setRole(new Role(authDTO.getIdRole()));
		auth.setUpdateDate(new Date());
		authDAO.update(auth);
	}
	

}
