package com.tampro.service;

import java.util.List;

import com.tampro.dto.Paging;
import com.tampro.dto.PublisherDTO;

public interface PublisherService {
	PublisherDTO findById(int id);
	List<PublisherDTO> findByProperty(String property, Object object);
	List<PublisherDTO> findAll(PublisherDTO publisherDTO,Paging paging);
	void save(PublisherDTO publisherDTO) throws Exception;
	void update(PublisherDTO publisherDTO) throws Exception;
	void delete(PublisherDTO publisherDTO) throws Exception;
}
