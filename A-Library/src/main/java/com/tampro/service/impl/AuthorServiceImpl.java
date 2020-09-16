package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.AuthorDAO;
import com.tampro.dto.AuthorDTO;
import com.tampro.dto.Paging;
import com.tampro.entity.Authors;
import com.tampro.service.AuthorService;
import com.tampro.utils.ConvertToDTO;

@Service
public class AuthorServiceImpl  implements AuthorService{
	@Autowired
	AuthorDAO<Authors>  authorDAO;

	public AuthorDTO findById(int id) {
		// TODO Auto-generated method stub
		Authors authors = authorDAO.findById(Authors.class, id);
		AuthorDTO authorDTO = ConvertToDTO.convertAuthorEntityToDTO(authors);
		return authorDTO;
	}

	public List<AuthorDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<AuthorDTO> authorDTOs = new ArrayList<AuthorDTO>();
		for(Authors authors : authorDAO.findByProperty(property, object)) {
			AuthorDTO authorDTO = ConvertToDTO.convertAuthorEntityToDTO(authors);
			authorDTOs.add(authorDTO);
		}
		return authorDTOs;
	}

	public List<AuthorDTO> findAll(AuthorDTO authorDTO, Paging paging) {
		StringBuilder queryStr = new StringBuilder();
		Map<String,Object> mapParams = new HashMap<String, Object>();
		if(authorDTO.getCode() != null && !StringUtils.isEmpty(authorDTO.getCode())) {
			queryStr.append(" and  model.code =:code");
			mapParams.put("code", authorDTO.getCode());
		}
		if(authorDTO.getName()!= null && !StringUtils.isEmpty(authorDTO.getName())) {
			queryStr.append(" and model.name like :name");
			mapParams.put("name","%"+authorDTO.getName()+"%");
		}
		List<AuthorDTO> list = new ArrayList<AuthorDTO>();
		for(Authors authors : authorDAO.findAll(queryStr.toString(), mapParams, paging)) {
			AuthorDTO dto = ConvertToDTO.convertAuthorEntityToDTO(authors);
			list.add(dto);
		}
		return list;
	}

	public void save(AuthorDTO authorDTO) throws Exception {
		// TODO Auto-generated method stub
		Authors authors = new Authors();
		authors.setDescription(authorDTO.getDescription());
		authors.setCode(authorDTO.getCode());
		authors.setName(authorDTO.getName());
		authors.setUpdateDate(new Date());
		authors.setActiveFlag(1);
		authors.setCreateDate(new Date());
		authorDAO.save(authors);
	}

	public void update(AuthorDTO authorDTO) throws Exception {
		// TODO Auto-generated method stub
		Authors authors = new Authors();
		authors.setDescription(authorDTO.getDescription());
		authors.setCode(authorDTO.getCode());
		authors.setId(authorDTO.getId());
		authors.setName(authorDTO.getName());
		authors.setUpdateDate(new Date());
		authors.setActiveFlag(authorDTO.getActiveFlag());
		authors.setCreateDate(authorDTO.getCreateDate());
		authorDAO.update(authors);
	}

	public void delete(AuthorDTO authorDTO) throws Exception {
		// TODO Auto-generated method stub
		Authors authors = new Authors();
		authors.setDescription(authorDTO.getDescription());
		authors.setId(authorDTO.getId());
		authors.setCode(authorDTO.getCode());
		authors.setName(authorDTO.getName());
		authors.setUpdateDate(new Date());
		authors.setActiveFlag(0);
		authors.setCreateDate(authorDTO.getCreateDate());
		authorDAO.delete(authors);
	}

	

}
