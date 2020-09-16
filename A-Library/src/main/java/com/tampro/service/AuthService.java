package com.tampro.service;

import java.util.List;

import com.tampro.dto.AuthDTO;
import com.tampro.dto.Paging;

public interface AuthService {
	AuthDTO findById(int id);
	List<AuthDTO> findByProperty(String property, Object object);
	List<AuthDTO> findAll(AuthDTO authDTO,Paging paging);
	void update(AuthDTO authDTO) throws Exception;

}
