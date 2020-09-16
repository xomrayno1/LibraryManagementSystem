package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.InvoiceDAO;
import com.tampro.dto.HistoryDTO;
import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.ProductInStockDTO;
import com.tampro.dto.ProductInfoDTO;
import com.tampro.entity.Invoice;
import com.tampro.entity.ProductInfo;
import com.tampro.entity.Users;
import com.tampro.service.InvoiceService;
import com.tampro.service.HistoryService;
import com.tampro.service.ProductInStockService;
import com.tampro.utils.Constant;
import com.tampro.utils.ConvertToDTO;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Autowired
	InvoiceDAO<Invoice> invoiceDAO;
	@Autowired
	HistoryService historyService;
	@Autowired
	ProductInStockService productInStockService;
	
	public InvoiceDTO findById(int id) {
		// TODO Auto-generated method stub
		Invoice invoice = invoiceDAO.findById(Invoice.class, id);
		InvoiceDTO invoiceDTO = ConvertToDTO.convertInvoiceEntityToDTO(invoice);
		return invoiceDTO;
	}

	public List<InvoiceDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<InvoiceDTO> list = new ArrayList<InvoiceDTO>();
		for(Invoice invoice : invoiceDAO.findByProperty(property, object)) {
			InvoiceDTO dto = ConvertToDTO.convertInvoiceEntityToDTO(invoice);
			list.add(dto);
		}
		return list;
	}

	public List<InvoiceDTO> findAll(InvoiceDTO invoiceDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(invoiceDTO != null) {
			if(invoiceDTO.getType() != 0 ) {
				queryStr.append(" and model.type =:type");
				mapParams.put("type", invoiceDTO.getType());
			}
			if(invoiceDTO.getFromDate()!= null && invoiceDTO.getToDate() != null) {
				queryStr.append(" and model.createDate   between  :fromDate and :toDate");
				mapParams.put("fromDate", invoiceDTO.getFromDate());
				mapParams.put("toDate", invoiceDTO.getToDate());
			}
		}
		List<InvoiceDTO> list = new ArrayList<InvoiceDTO>();	
		for(Invoice invoice : invoiceDAO.findAll(queryStr.toString(), mapParams, paging)) {
			InvoiceDTO dto = ConvertToDTO.convertInvoiceEntityToDTO(invoice);
			list.add(dto);
		}
		return list;
	}

	public void save(InvoiceDTO invoiceDTO) throws Exception { /// --
		// TODO Auto-generated method stub
		Invoice invoice = new Invoice();
		invoice.setActiveFlag(1);
		invoice.setCreateDate(new Date());
		invoice.setUpdateDate(new Date());
		invoice.setUsers(new Users(invoiceDTO.getUsersDTO().getId()));
		invoice.setQuantity(invoiceDTO.getQuantity());
		invoice.setProductInfo(new ProductInfo(invoiceDTO.getIdProduct()));
		
		HistoryDTO historyDTO = new HistoryDTO();
		historyDTO.setProductInfoDTO(new ProductInfoDTO(invoiceDTO.getIdProduct()));
		historyDTO.setQuantity(invoiceDTO.getQuantity());
		historyDTO.setUsersDTO(invoiceDTO.getUsersDTO());
		List<ProductInStockDTO> productInStockDTOs = productInStockService.findByProperty("productInfo.id", invoiceDTO.getIdProduct());
		if(invoiceDTO.getType() == Constant.GOODS_RECEIPT) {
			historyDTO.setActionName(Constant.ACTION_RECEIPT);
			invoice.setType(Constant.GOODS_RECEIPT);
			historyDTO.setType(Constant.GOODS_RECEIPT);			
			if(!productInStockDTOs.isEmpty()) {
				productInStockDTOs.get(0).setQuantity(productInStockDTOs.get(0).getQuantity() + invoiceDTO.getQuantity());
			}
		}else {
			historyDTO.setActionName(Constant.ACTION_ISSUE);
			invoice.setType(Constant.GOODS_ISSUE);
			historyDTO.setType(Constant.GOODS_ISSUE);
			if(!productInStockDTOs.isEmpty()) {
				productInStockDTOs.get(0).setQuantity(productInStockDTOs.get(0).getQuantity() - invoiceDTO.getQuantity());
			}
		}
		invoiceDAO.save(invoice);
		historyService.save(historyDTO);
		productInStockService.update(productInStockDTOs.get(0));
		
	}

	public void update(InvoiceDTO invoiceDTO) throws Exception {
		// TODO Auto-generated method stub
		Invoice invoice = new Invoice();
		invoice.setActiveFlag(invoiceDTO.getActiveFlag());
		invoice.setCreateDate(invoiceDTO.getCreateDate());
		invoice.setId(invoiceDTO.getId());
		invoice.setUpdateDate(new Date());
		invoice.setUsers(new Users(invoiceDTO.getUsersDTO().getId()));
		invoice.setQuantity(invoiceDTO.getQuantity());
		invoice.setProductInfo(new ProductInfo(invoiceDTO.getIdProduct()));
		
		HistoryDTO historyDTO = new HistoryDTO();
		historyDTO.setActionName(Constant.ACTION_EDIT);
		historyDTO.setProductInfoDTO(new ProductInfoDTO(invoiceDTO.getIdProduct()));
		historyDTO.setQuantity(invoiceDTO.getQuantity());
		historyDTO.setUsersDTO(invoiceDTO.getUsersDTO());
		
		Invoice invoiceCurrent = invoiceDAO.findById(Invoice.class,invoiceDTO.getId());
		
		if(invoiceDTO.getType() == Constant.GOODS_RECEIPT) {
			invoice.setType(Constant.GOODS_RECEIPT);
			historyDTO.setType(Constant.GOODS_RECEIPT);						
		}else {
			invoice.setType(Constant.GOODS_ISSUE);
			historyDTO.setType(Constant.GOODS_ISSUE);			
		}
		List<ProductInStockDTO> productInStockDTOs = null;
		if(invoiceCurrent.getProductInfo().getId() != invoiceDTO.getIdProduct()) {
			productInStockDTOs = productInStockService.findByProperty("productInfo.id", invoiceCurrent.getProductInfo().getId());
			if(invoiceDTO.getType() == Constant.GOODS_RECEIPT) {
				if(!productInStockDTOs.isEmpty()) {
					productInStockDTOs.get(0).setQuantity((productInStockDTOs.get(0).getQuantity() - invoiceCurrent.getQuantity()) );
				}
			}else {
				if(!productInStockDTOs.isEmpty()) {
					productInStockDTOs.get(0).setQuantity((productInStockDTOs.get(0).getQuantity() + invoiceCurrent.getQuantity()) );
				}
			}
			productInStockService.update(productInStockDTOs.get(0));
			productInStockDTOs = productInStockService.findByProperty("productInfo.id", invoiceDTO.getIdProduct());
			
			
			if(invoiceDTO.getType() == Constant.GOODS_RECEIPT) {
				if(!productInStockDTOs.isEmpty()) {
					productInStockDTOs.get(0).setQuantity(productInStockDTOs.get(0).getQuantity()+ invoiceDTO.getQuantity());
				}
			}else {
				if(!productInStockDTOs.isEmpty()) {
					productInStockDTOs.get(0).setQuantity(productInStockDTOs.get(0).getQuantity() - invoiceDTO.getQuantity());
				}
			}
		}else {
			productInStockDTOs = productInStockService.findByProperty("productInfo.id", invoiceDTO.getIdProduct());
			if(invoiceDTO.getType() == Constant.GOODS_RECEIPT) {
				if(!productInStockDTOs.isEmpty()) {
					productInStockDTOs.get(0).setQuantity((productInStockDTOs.get(0).getQuantity() - invoiceCurrent.getQuantity()) + invoiceDTO.getQuantity());
				}
			}else {
				if(!productInStockDTOs.isEmpty()) {
					productInStockDTOs.get(0).setQuantity((productInStockDTOs.get(0).getQuantity() + invoiceCurrent.getQuantity()) - invoiceDTO.getQuantity());
				}
			}
		}
		invoiceDAO.update(invoice);
		historyService.save(historyDTO);
		productInStockService.update(productInStockDTOs.get(0));
	}


}
