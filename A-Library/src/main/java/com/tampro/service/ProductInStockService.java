package com.tampro.service;

import java.util.List;

import com.tampro.dto.Paging;
import com.tampro.dto.ProductInStockDTO;

public interface ProductInStockService {
	ProductInStockDTO findById(int id);
	List<ProductInStockDTO> findByProperty(String property, Object object);
	List<ProductInStockDTO> findAll(ProductInStockDTO productInStockDTO,Paging paging);
	void save(ProductInStockDTO productInStockDTO) throws Exception;
	void update(ProductInStockDTO productInStockDTO) throws Exception;
	void delete(ProductInStockDTO productInStockDTO) throws Exception;
}
