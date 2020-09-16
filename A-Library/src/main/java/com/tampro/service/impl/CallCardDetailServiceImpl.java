package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.CallCardDetailDAO;
import com.tampro.dto.CallCardDetailDTO;
import com.tampro.dto.HistoryDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.ProductInStockDTO;
import com.tampro.dto.UsersDTO;
import com.tampro.entity.CallCard;
import com.tampro.entity.CallCardDetail;
import com.tampro.entity.ProductInfo;
import com.tampro.service.CallCardDetailService;
import com.tampro.service.HistoryService;
import com.tampro.service.ProductInStockService;
import com.tampro.utils.Constant;
import com.tampro.utils.ConvertToDTO;

@Service
public class CallCardDetailServiceImpl implements CallCardDetailService{

	@Autowired
	CallCardDetailDAO<CallCardDetail> cardDetailDAO;
	@Autowired
	HistoryService historyService;
	@Autowired
	ProductInStockService inStockService;
	
	public CallCardDetailDTO findById(int id) {
		// TODO Auto-generated method stub
		CallCardDetail callCardDetail = cardDetailDAO.findById(CallCardDetail.class,id);
		CallCardDetailDTO callCardDetailDTO = ConvertToDTO.convertCallCardDetailEntityToDTO(callCardDetail);
		return callCardDetailDTO;
	}

	public List<CallCardDetailDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<CallCardDetailDTO> list = new ArrayList<CallCardDetailDTO>();
		for(CallCardDetail callCardDetail : cardDetailDAO.findByProperty(property, object)) {
			CallCardDetailDTO dto = ConvertToDTO.convertCallCardDetailEntityToDTO(callCardDetail);
			list.add(dto);
		}
		return list;
	}

	public List<CallCardDetailDTO> findAll(CallCardDetailDTO callCardDetailDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String,Object> mapParams = new HashMap<String,Object>();
		List<CallCardDetailDTO> list = new ArrayList<CallCardDetailDTO>();
		for(CallCardDetail callCardDetail : cardDetailDAO.findAll(queryStr.toString(), mapParams, paging)) {
			CallCardDetailDTO dto = ConvertToDTO.convertCallCardDetailEntityToDTO(callCardDetail);
			list.add(dto);
		}
		return list;
	}

	public void save(CallCardDetailDTO callCardDetailDTO,UsersDTO usersDTO) throws Exception {
		// TODO Auto-generated method stub
		CallCardDetail callCardDetail = new CallCardDetail();
		callCardDetail.setActiveFlag(1);
		callCardDetail.setCallCard(new CallCard(callCardDetailDTO.getCallCardId()));
		callCardDetail.setCreateDate(new Date());
		callCardDetail.setDueDate(callCardDetailDTO.getDueDate());
		callCardDetail.setProductInfo(new ProductInfo(callCardDetailDTO.getProductInfoDTO().getId()));
		callCardDetail.setReturnDate(callCardDetailDTO.getReturnDate());
		callCardDetail.setStatus(callCardDetailDTO.getStatus());
		callCardDetail.setUpdateDate(new Date());
		cardDetailDAO.save(callCardDetail);
		
		HistoryDTO historyDTO = new HistoryDTO();
		historyDTO.setActionName(Constant.ACTION_BRROW);
		historyDTO.setProductInfoDTO(callCardDetailDTO.getProductInfoDTO());
		historyDTO.setType(Constant.BORROW);
		historyDTO.setQuantity(1);
		historyDTO.setUsersDTO(usersDTO);
		historyService.save(historyDTO);
		
		List<ProductInStockDTO> productInStockDTOs = inStockService.findByProperty("productInfo.id", callCardDetail.getProductInfo().getId());
		if(!productInStockDTOs.isEmpty()) {
			productInStockDTOs.get(0).setQuantity(productInStockDTOs.get(0).getQuantity() - 1);
			inStockService.update(productInStockDTOs.get(0));
		}
	}

	public void update(CallCardDetailDTO callCardDetailDTO,UsersDTO usersDTO) throws Exception {
		// TODO Auto-generated method stub
		CallCardDetail callCardDetail = new CallCardDetail();
		callCardDetail.setActiveFlag(callCardDetailDTO.getActiveFlag());
		callCardDetail.setCallCard(new CallCard(callCardDetailDTO.getCallCardId()));
		callCardDetail.setCreateDate(callCardDetailDTO.getCreateDate());
		callCardDetail.setDueDate(callCardDetailDTO.getDueDate());
		callCardDetail.setId(callCardDetailDTO.getId());
		callCardDetail.setProductInfo(new ProductInfo(callCardDetailDTO.getProductInfoDTO().getId()));
		callCardDetail.setReturnDate(callCardDetailDTO.getReturnDate());
		callCardDetail.setStatus(callCardDetailDTO.getStatus());
		callCardDetail.setUpdateDate(new Date());
		cardDetailDAO.update(callCardDetail);
		
		HistoryDTO historyDTO = new HistoryDTO();
		historyDTO.setActionName(Constant.ACTION_RETURN);
		historyDTO.setProductInfoDTO(callCardDetailDTO.getProductInfoDTO());
		historyDTO.setType(Constant.RETURN);
		historyDTO.setQuantity(1);
		historyDTO.setUsersDTO(usersDTO);
		historyService.save(historyDTO);
		
		List<ProductInStockDTO> productInStockDTOs = inStockService.findByProperty("productInfo.id", callCardDetail.getProductInfo().getId());
		if(!productInStockDTOs.isEmpty()) {
			productInStockDTOs.get(0).setQuantity(productInStockDTOs.get(0).getQuantity() + 1);
			inStockService.update(productInStockDTOs.get(0));
		}
		
		
	}

	public void delete(CallCardDetailDTO callCardDetailDTO) throws Exception {
		// TODO Auto-generated method stub
		CallCardDetail callCardDetail = new CallCardDetail();
		callCardDetail.setActiveFlag(0);
		callCardDetail.setCallCard(new CallCard(callCardDetailDTO.getCallCardId()));
		callCardDetail.setCreateDate(callCardDetailDTO.getCreateDate());
		callCardDetail.setDueDate(callCardDetailDTO.getDueDate());
		callCardDetail.setId(callCardDetailDTO.getId());
		callCardDetail.setProductInfo(new ProductInfo(callCardDetailDTO.getProductInfoDTO().getId()));
		callCardDetail.setReturnDate(callCardDetailDTO.getReturnDate());
		callCardDetail.setStatus(callCardDetailDTO.getStatus());
		callCardDetail.setUpdateDate(new Date());
		cardDetailDAO.delete(callCardDetail);
	}

	public List<CallCardDetailDTO> findAllFinish(CallCardDetailDTO callCardDetailDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String,Object> mapParams = new HashMap<String,Object>();
		queryStr.append(" order by model.id desc ");
		List<CallCardDetailDTO> list = new ArrayList<CallCardDetailDTO>();
		for(CallCardDetail callCardDetail : cardDetailDAO.findAllFinish(queryStr.toString(), mapParams, paging)) {
			CallCardDetailDTO dto = ConvertToDTO.convertCallCardDetailEntityToDTO(callCardDetail);
			list.add(dto);
		}
		return list;
	}
	

}
