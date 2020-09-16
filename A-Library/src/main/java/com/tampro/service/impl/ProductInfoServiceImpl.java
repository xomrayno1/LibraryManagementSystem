package com.tampro.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.ProductDAO;
import com.tampro.dto.Paging;
import com.tampro.dto.ProductInStockDTO;
import com.tampro.dto.ProductInfoDTO;
import com.tampro.entity.Authors;
import com.tampro.entity.Category;
import com.tampro.entity.ProductInfo;
import com.tampro.entity.Publisher;
import com.tampro.service.ProductInStockService;
import com.tampro.service.ProductInfoService;
import com.tampro.utils.ConvertToDTO;

@Service
public class ProductInfoServiceImpl  implements ProductInfoService{

	@Autowired
	ProductDAO<ProductInfo> productDAO;
	@Autowired
	ProductInStockService inStockService;
	
	public List<ProductInfoDTO> findByProperty(String property, Object object) {
		List<ProductInfoDTO> listProductsDTOs = new ArrayList<ProductInfoDTO>();
		for(ProductInfo product : productDAO.findByProperty(property, object)) {
			ProductInfoDTO infoDTO = ConvertToDTO.convertProductInfoEntityToDTO(product);
			listProductsDTOs.add(infoDTO);
		}
		return listProductsDTOs;
	}

	public List<ProductInfoDTO> findAll(ProductInfoDTO productsDTO, Paging pagging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder("");
		Map<String,Object> mapParams = new HashMap<String, Object>();
		if(productsDTO != null) {
			if(productsDTO.getName() != null && !StringUtils.isEmpty(productsDTO.getName())) {
				queryStr.append(" and  model.name like  :name");
				mapParams.put("name", "%"+productsDTO.getName()+"%");
			}
			if(productsDTO.getCode() != null && !StringUtils.isEmpty(productsDTO.getCode())) {
				queryStr.append(" and model.code =:code");
				mapParams.put("code", productsDTO.getCode());
			}
			if(productsDTO.getAuthorId() != null && productsDTO.getAuthorId() != 0) {
				queryStr.append(" and model.authors.id =:idAuthor");
				mapParams.put("idAuthor", productsDTO.getAuthorId());
			}
			if(productsDTO.getAuthorId() != null && productsDTO.getCateId() != 0) {
				queryStr.append(" and model.category.id =:idCate");
				mapParams.put("idCate", productsDTO.getCateId());
			}
			if(productsDTO.getAuthorId() != null && productsDTO.getPublisherId() != 0) {
				queryStr.append(" and model.company.id =:idPub");
				mapParams.put("idPub", productsDTO.getPublisherId());
			}			
		}
		List<ProductInfoDTO> listProductsDTOs = new ArrayList<ProductInfoDTO>();
		for(ProductInfo product : productDAO.findAll(queryStr.toString(), mapParams, pagging)) {
			ProductInfoDTO infoDTO = ConvertToDTO.convertProductInfoEntityToDTO(product);
			listProductsDTOs.add(infoDTO);			
		}
		
		return listProductsDTOs;
	}



	public void save(ProductInfoDTO productsDTO)throws Exception {
		// TODO Auto-generated method stub
		ProductInfo productInfo = new ProductInfo();
		productInfo.setActiveFlag(1);
		productInfo.setAuthors(new Authors(productsDTO.getAuthorId()));
		productInfo.setCategory(new Category(productsDTO.getCateId()));
		productInfo.setCode(productsDTO.getCode());
		productInfo.setCompany(new Publisher(productsDTO.getPublisherId()));
		productInfo.setCreateDate(new Date());
		productInfo.setName(productsDTO.getName());
		productInfo.setUpdateDate(new Date());
		String img = System.currentTimeMillis()+"_"+productsDTO.getMultipartFile().getOriginalFilename();	
		productInfo.setImgUrl("/images/upload/"+img);
		File file = new File("C:\\Users\\Admin\\eclipse-workspace\\A-Library\\src\\main\\webapp\\static\\images\\upload\\"+img);
		productsDTO.getMultipartFile().transferTo(file);
		
		productDAO.save(productInfo);
		ProductInStockDTO productInStockDTO  = new ProductInStockDTO();
		ProductInfoDTO  productInfoDTO = ConvertToDTO.convertProductInfoEntityToDTO(productInfo);
		productInStockDTO.setProductInfoDTO(productInfoDTO);
		productInStockDTO.setQuantity(1);
		inStockService.save(productInStockDTO);
	}

	public void update(ProductInfoDTO productsDTO) throws Exception {
		// TODO Auto-generated method stub
		ProductInfo productInfo = new ProductInfo();
		productInfo.setActiveFlag(productsDTO.getActiveFlag());
		productInfo.setAuthors(new Authors(productsDTO.getAuthorId()));
		productInfo.setCategory(new Category(productsDTO.getCateId()));
		productInfo.setCode(productsDTO.getCode());
		productInfo.setCompany(new Publisher(productsDTO.getPublisherId()));
		productInfo.setCreateDate(productsDTO.getCreateDate());
		productInfo.setId(productsDTO.getId());
		if(productsDTO.getMultipartFile().getOriginalFilename().isEmpty()) {
			productInfo.setImgUrl(productsDTO.getImgUrl());
		}else {
			String img = System.currentTimeMillis()+"_"+productsDTO.getMultipartFile().getOriginalFilename();
			File file = new File("C:\\Users\\Admin\\eclipse-workspace\\A-Library\\src\\main\\webapp\\static\\images\\upload\\"+img);
			productsDTO.getMultipartFile().transferTo(file);
			productInfo.setImgUrl("/images/upload/"+img);
		}
		productInfo.setName(productsDTO.getName());
		productInfo.setUpdateDate(new Date());
		productDAO.update(productInfo);
		
		
	}

	public void delete(ProductInfoDTO productsDTO) throws Exception {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setActiveFlag(0);
		productInfo.setAuthors(new Authors(productsDTO.getAuthorId()));
		productInfo.setCategory(new Category(productsDTO.getCateId()));
		productInfo.setCode(productsDTO.getCode());
		productInfo.setCompany(new Publisher(productsDTO.getPublisherId()));
		productInfo.setCreateDate(productsDTO.getCreateDate());
		productInfo.setId(productsDTO.getId());
		productInfo.setImgUrl(productsDTO.getImgUrl());
		productInfo.setName(productsDTO.getName());
		productInfo.setUpdateDate(new Date());
		productDAO.delete(productInfo);
		List<ProductInStockDTO> productInStockDTOs  = inStockService.findByProperty("productInfo.id", productsDTO.getId());
		if(!productInStockDTOs.isEmpty()) {
			inStockService.delete(productInStockDTOs.get(0));
		}
	}

	public ProductInfoDTO findById(int id) {
		ProductInfo productInfo = productDAO.findById(ProductInfo.class, id);
		ProductInfoDTO productInfoDTO = ConvertToDTO.convertProductInfoEntityToDTO(productInfo);
		
		return productInfoDTO;
	}
	

}
