package com.tampro.service;

import java.util.List;

import com.tampro.dto.Paging;
import com.tampro.dto.ReadersDTO;

public interface ReadersService {

	ReadersDTO findById(int id);
	List<ReadersDTO> findByProperty(String property, Object object);
	List<ReadersDTO> findAll(ReadersDTO readersDTO,Paging paging);
	void save(ReadersDTO readersDTO) throws Exception;
	void update(ReadersDTO readersDTO) throws Exception;
	void delete(ReadersDTO readersDTO) throws Exception;
}
