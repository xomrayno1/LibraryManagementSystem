package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.ProductInStockDAO;
import com.tampro.dto.Paging;
import com.tampro.dto.ProductInStockDTO;
import com.tampro.entity.ProductInStock;
import com.tampro.entity.ProductInfo;
import com.tampro.service.ProductInStockService;
import com.tampro.utils.ConvertToDTO;

@Service
public class ProductInStockServiceImpl  implements ProductInStockService{

	@Autowired
	ProductInStockDAO<ProductInStock> productInStockDAO;
	
	public ProductInStockDTO findById(int id) {
		ProductInStock productInStock = productInStockDAO.findById(ProductInStock.class, id);
		// TODO Auto-generated method stub
		ProductInStockDTO productInStockDTO = ConvertToDTO.convertProductInStockEntityToDTO(productInStock);
		return productInStockDTO;
	}

	public List<ProductInStockDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<ProductInStockDTO> productInStockDTOs = new ArrayList<ProductInStockDTO>();
		for(ProductInStock inStock : productInStockDAO.findByProperty(property, object)) {
			ProductInStockDTO productInStockDTO = ConvertToDTO.convertProductInStockEntityToDTO(inStock);
			productInStockDTOs.add(productInStockDTO);
		}
		return productInStockDTOs;
	}

	public List<ProductInStockDTO> findAll(ProductInStockDTO productInStockDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(productInStockDTO != null) {
			if(productInStockDTO.getProductInfoDTO() !=null) {
				if(productInStockDTO.getProductInfoDTO().getName() !=null && !StringUtils.isEmpty(productInStockDTO.getProductInfoDTO().getName())) {
					queryStr.append(" and model.productInfo.name like  :name");
					mapParams.put("name", "%"+productInStockDTO.getProductInfoDTO().getName()+"%");
				}
			}
		}
		List<ProductInStockDTO> productInStockDTOs = new ArrayList<ProductInStockDTO>();
		for(ProductInStock inStock : productInStockDAO.findAll(queryStr.toString(), mapParams, paging)) {
			ProductInStockDTO dto = ConvertToDTO.convertProductInStockEntityToDTO(inStock);
			productInStockDTOs.add(dto);
		}
		return productInStockDTOs;
	}

	public void save(ProductInStockDTO productInStockDTO) throws Exception {
		// TODO Auto-generated method stub
		ProductInStock inStock = new ProductInStock();
		inStock.setActiveFlag(1);
		inStock.setCreateDate(new Date());
		inStock.setProductInfo(new ProductInfo(productInStockDTO.getProductInfoDTO().getId()));
		inStock.setQuanity(productInStockDTO.getQuantity());
		inStock.setUpdateDate(new Date());
		productInStockDAO.save(inStock);
	}

	public void update(ProductInStockDTO productInStockDTO) throws Exception {
		// TODO Auto-generated method stub
		ProductInStock inStock = new ProductInStock();
		inStock.setActiveFlag(productInStockDTO.getActiveFlag());
		inStock.setCreateDate(productInStockDTO.getCreateDate());
		inStock.setId(productInStockDTO.getId());
		inStock.setProductInfo(new ProductInfo(productInStockDTO.getProductInfoDTO().getId()));
		inStock.setQuanity(productInStockDTO.getQuantity());
		inStock.setUpdateDate(productInStockDTO.getUpdateDate());
		productInStockDAO.update(inStock);
	}

	public void delete(ProductInStockDTO productInStockDTO) throws Exception {
		// TODO Auto-generated method stub
		ProductInStock inStock = new ProductInStock();
		inStock.setActiveFlag(0);
		inStock.setCreateDate(productInStockDTO.getCreateDate());
		inStock.setId(productInStockDTO.getId());
		inStock.setProductInfo(new ProductInfo(productInStockDTO.getProductInfoDTO().getId()));
		inStock.setQuanity(productInStockDTO.getQuantity());
		inStock.setUpdateDate(productInStockDTO.getUpdateDate());
		productInStockDAO.delete(inStock);
	}

}
