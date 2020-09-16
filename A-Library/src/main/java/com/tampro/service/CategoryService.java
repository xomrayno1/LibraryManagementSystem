package com.tampro.service;

import java.util.List;

import com.tampro.dto.CategoryDTO;
import com.tampro.dto.Paging;

public interface CategoryService {
	CategoryDTO findById(int id);
	List<CategoryDTO> findByProperty(String property, Object object);
	List<CategoryDTO> findAll(CategoryDTO categoryDTO,Paging paging);
	void save(CategoryDTO categoryDTO) throws Exception;
	void update(CategoryDTO categoryDTO) throws Exception;
	void delete(CategoryDTO categoryDTO) throws Exception;

}
