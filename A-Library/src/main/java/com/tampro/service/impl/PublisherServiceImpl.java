package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.PublisherDAO;
import com.tampro.dto.Paging;
import com.tampro.dto.PublisherDTO;
import com.tampro.entity.Publisher;
import com.tampro.service.PublisherService;
import com.tampro.utils.ConvertToDTO;

@Service
public class PublisherServiceImpl  implements PublisherService{

	@Autowired
	PublisherDAO<Publisher> publisherDAO;

	public PublisherDTO findById(int id) {
		// TODO Auto-generated method stub
		Publisher publisher = publisherDAO.findById(Publisher.class, id);
		PublisherDTO publisherDTO = ConvertToDTO.convertPublisherEntityToDTO(publisher);
		return publisherDTO;
	}

	public List<PublisherDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<PublisherDTO> publisherDTOs = new ArrayList<PublisherDTO>();
		for(Publisher publisher : publisherDAO.findByProperty(property, object)) {
			PublisherDTO publisherDTO = ConvertToDTO.convertPublisherEntityToDTO(publisher);
			publisherDTOs.add(publisherDTO);
		}
		return publisherDTOs;
	}

	public List<PublisherDTO> findAll(PublisherDTO publisherDTO, Paging paging) {
		StringBuilder queryStr = new StringBuilder();
		Map<String,Object> mapParams = new HashMap<String, Object>();
		if(publisherDTO.getCode() != null && !StringUtils.isEmpty(publisherDTO.getCode())) {
			queryStr.append(" and model.code =:code");
			mapParams.put("code", publisherDTO.getCode());
		}
		if(publisherDTO.getName() != null && !StringUtils.isEmpty(publisherDTO.getName())) {
			queryStr.append(" and  model.name  like :name");
			mapParams.put("name","%"+ publisherDTO.getName()+"%");
		}		
		List<PublisherDTO> publisherDTOs = new ArrayList<PublisherDTO>();
		for(Publisher publisher : publisherDAO.findAll(queryStr.toString(), mapParams, paging)) {
			PublisherDTO dto = ConvertToDTO.convertPublisherEntityToDTO(publisher);
			publisherDTOs.add(dto);
		}
		return publisherDTOs;
	}

	public void save(PublisherDTO publisherDTO) throws Exception {
		// TODO Auto-generated method stub
		Publisher publisher = new Publisher();
		publisher.setAddress(publisherDTO.getAddress());
		publisher.setCode(publisherDTO.getCode());
		publisher.setEmail(publisherDTO.getEmail());
		publisher.setInfo(publisherDTO.getInfo());
		publisher.setName(publisherDTO.getName());
		publisher.setPhone(publisherDTO.getPhone());
		publisher.setActiveFlag(1);
		publisher.setUpdateDate(new Date());
		publisher.setCreateDate(new Date());
		publisherDAO.save(publisher);
	}

	public void update(PublisherDTO publisherDTO) throws Exception {
		// TODO Auto-generated method stub
		Publisher publisher = new Publisher();
		publisher.setAddress(publisherDTO.getAddress());
		publisher.setCode(publisherDTO.getCode());
		publisher.setEmail(publisherDTO.getEmail());
		publisher.setId(publisherDTO.getId());
		publisher.setInfo(publisherDTO.getInfo());
		publisher.setName(publisherDTO.getName());
		publisher.setPhone(publisherDTO.getPhone());
		publisher.setActiveFlag(publisherDTO.getActiveFlag());
		publisher.setUpdateDate(new Date());
		publisher.setCreateDate(publisherDTO.getCreateDate());
		publisherDAO.update(publisher);
	}

	public void delete(PublisherDTO publisherDTO) throws Exception {
		// TODO Auto-generated method stub
		Publisher publisher = new Publisher();
		publisher.setAddress(publisherDTO.getAddress());
		publisher.setCode(publisherDTO.getCode());
		publisher.setEmail(publisherDTO.getEmail());
		publisher.setId(publisherDTO.getId());
		publisher.setInfo(publisherDTO.getInfo());
		publisher.setName(publisherDTO.getName());
		publisher.setPhone(publisherDTO.getPhone());
		publisher.setActiveFlag(0);
		publisher.setUpdateDate(publisherDTO.getUpdateDate());
		publisher.setCreateDate(new Date());
		publisherDAO.delete(publisher);
	}

	
	
	
	
}
