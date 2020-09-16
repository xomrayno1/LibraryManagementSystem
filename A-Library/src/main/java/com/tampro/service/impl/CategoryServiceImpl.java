package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.CategoryDAO;
import com.tampro.dto.CategoryDTO;
import com.tampro.dto.Paging;
import com.tampro.entity.Category;
import com.tampro.service.CategoryService;
import com.tampro.utils.ConvertToDTO;

@Service
public class CategoryServiceImpl  implements CategoryService{
	
	@Autowired
	CategoryDAO<Category> categoryDAO;

	public List<CategoryDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		for(Category category : categoryDAO.findByProperty(property, object)) {
			CategoryDTO categoryDTO = ConvertToDTO.convertCategoryEntityToDTO(category);
			list.add(categoryDTO);
		}
		return list;
	}

	public List<CategoryDTO> findAll(CategoryDTO categoryDTO, Paging pagging) {
		StringBuilder queryStr = new StringBuilder("");
		Map<String,Object> mapParams = new HashMap<String, Object>();
		if(categoryDTO.getName() != null && !StringUtils.isEmpty(categoryDTO.getName())) {
			System.out.println(categoryDTO.getName());
			queryStr.append(" and  model.name like :name");
			mapParams.put("name", "%"+categoryDTO.getName()+"%");			
		}
		if(categoryDTO.getCode() != null && !StringUtils.isEmpty(categoryDTO.getCode())) {
			System.out.println(categoryDTO.getCode());
			queryStr.append(" and model.code =:code");
			mapParams.put("code", categoryDTO.getCode());					
		}
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		for(Category category : categoryDAO.findAll(queryStr.toString(), mapParams, pagging)) {
			CategoryDTO cate = ConvertToDTO.convertCategoryEntityToDTO(category);
			list.add(cate);
		}
		return list;
	}

	public long countRows(String property, Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void save(CategoryDTO categoryDTO)  throws Exception{
		// TODO Auto-generated method stub
		Category category = new Category();
		category.setActiveFlag(1);
		category.setCode(categoryDTO.getCode());
		category.setCreateDate(new Date());
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setUpdateDate(new Date());
		categoryDAO.save(category);
	}

	public void update(CategoryDTO categoryDTO) throws Exception {
		// TODO Auto-generated method stub
		Category category = new Category();
		category.setActiveFlag(categoryDTO.getActiveFlag());
		category.setCode(categoryDTO.getCode());
		category.setCreateDate(categoryDTO.getCreateDate());
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setUpdateDate(new Date());
		categoryDAO.update(category);
	}

	public void delete(CategoryDTO categoryDTO) throws Exception {
		// TODO Auto-generated method stub
		Category category = new Category();
		category.setActiveFlag(0);
		category.setCode(categoryDTO.getCode());
		category.setCreateDate(categoryDTO.getCreateDate());
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setUpdateDate(new Date());
		categoryDAO.delete(category);
	}

	public CategoryDTO findById(int id) {
		Category category = categoryDAO.findById(Category.class, id);
		CategoryDTO categoryDTO = ConvertToDTO.convertCategoryEntityToDTO(category);
		return categoryDTO;
	}

}
