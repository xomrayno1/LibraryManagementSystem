package com.tampro.service;

import java.util.List;

import com.tampro.dto.Paging;
import com.tampro.dto.ProductInfoDTO;

public interface ProductInfoService {

	ProductInfoDTO findById(int id);
	List<ProductInfoDTO> findByProperty(String property, Object object);
	List<ProductInfoDTO> findAll(ProductInfoDTO productInfoDTO,Paging paging);
	void save(ProductInfoDTO productInfoDTO) throws Exception;
	void update(ProductInfoDTO productInfoDTO) throws Exception;
	void delete(ProductInfoDTO productInfoDTO) throws Exception;
}
