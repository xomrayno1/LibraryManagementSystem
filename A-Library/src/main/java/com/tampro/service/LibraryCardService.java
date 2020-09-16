package com.tampro.service;

import java.util.List;

import com.tampro.dto.LibraryCardDTO;
import com.tampro.dto.Paging;

public interface LibraryCardService {
	LibraryCardDTO findById(int id);
	List<LibraryCardDTO> findByProperty(String property, Object object);
	List<LibraryCardDTO> findAll(LibraryCardDTO libraryCardDTO,Paging paging);
	void save(LibraryCardDTO libraryCardDTO) throws Exception;
	void update(LibraryCardDTO libraryCardDTO) throws Exception;
	void delete(LibraryCardDTO libraryCardDTO) throws Exception;
}
