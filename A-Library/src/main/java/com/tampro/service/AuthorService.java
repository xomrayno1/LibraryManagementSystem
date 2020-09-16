package com.tampro.service;

import java.util.List;

import com.tampro.dto.AuthorDTO;
import com.tampro.dto.Paging;

public interface AuthorService {

	AuthorDTO findById(int id);
	List<AuthorDTO> findByProperty(String property, Object object);
	List<AuthorDTO> findAll(AuthorDTO authorDTO,Paging paging);
	void save(AuthorDTO authorDTO) throws Exception;
	void update(AuthorDTO authorDTO) throws Exception;
	void delete(AuthorDTO authorDTO) throws Exception;
}
