package com.tampro.service;

import java.util.List;

import com.tampro.dto.Paging;
import com.tampro.dto.UsersDTO;

public interface UserService {
	
	List<UsersDTO> findAll(UsersDTO usersDTO,Paging paging);
	public List<UsersDTO> findByProperty(String property , Object object);
	UsersDTO findById(int id);
	void save(UsersDTO usersDTO) throws Exception;
	void update(UsersDTO usersDTO) throws Exception;
	void delete(UsersDTO usersDTO) throws Exception;
}
