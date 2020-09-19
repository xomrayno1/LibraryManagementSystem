package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.CallCardDAO;
import com.tampro.dto.CallCardDTO;
import com.tampro.dto.Paging;
import com.tampro.entity.CallCard;
import com.tampro.entity.LibraryCard;
import com.tampro.entity.Users;
import com.tampro.service.CallCardService;
import com.tampro.utils.ConvertToDTO;

@Service
public class CallCardServiceImpl  implements CallCardService{

	@Autowired
	CallCardDAO<CallCard> callCardDAO;
	
	public CallCardDTO findById(int id) {
		// TODO Auto-generated method stub
		CallCard callCard = callCardDAO.findById(CallCard.class, id);
		CallCardDTO callCardDTO = ConvertToDTO.convertCallCardEntityToDTO(callCard);
		return callCardDTO;
	}

	public List<CallCardDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<CallCardDTO> list = new ArrayList<CallCardDTO>();
		for(CallCard callCard : callCardDAO.findByProperty(property, object)) {
			CallCardDTO dto = ConvertToDTO.convertCallCardEntityToDTO(callCard);
			list.add(dto);
		}		
		return list;
	}


	public int save(CallCardDTO callCardDTO) throws Exception {
		// TODO Auto-generated method stub
		CallCard callCard = new CallCard();
		callCard.setActiveFlag(1);
		callCard.setCreateDate(new Date());
		callCard.setDateIssue(callCardDTO.getDateIssue());
		callCard.setLibaryCard(new LibraryCard(callCardDTO.getLibaryCardDTO().getId()));
		callCard.setStatus(callCardDTO.getStatus());
		callCard.setUpdateDate(new Date());
		callCard.setUsers(new Users(callCardDTO.getUsersDTO().getId()));
		return callCardDAO.saveInt(callCard);
	}

	public void update(CallCardDTO callCardDTO) throws Exception {
		// TODO Auto-generated method stub
		CallCard callCard = new CallCard();
		callCard.setActiveFlag(callCardDTO.getActiveFlag());
		callCard.setCreateDate(callCardDTO.getCreateDate());
		callCard.setDateIssue(callCardDTO.getDateIssue());
		callCard.setId(callCardDTO.getId());
		callCard.setLibaryCard(new LibraryCard(callCardDTO.getLibaryCardDTO().getId()));
		callCard.setStatus(callCardDTO.getStatus());
		callCard.setUpdateDate(new Date());
		callCard.setUsers(new Users(callCardDTO.getUsersDTO().getId()));
		callCardDAO.update(callCard);
	}

	public void delete(CallCardDTO callCardDTO) throws Exception {
		// TODO Auto-generated method stub
		CallCard callCard = new CallCard();
		callCard.setActiveFlag(0);
		callCard.setId(callCardDTO.getId());
		callCard.setCreateDate(callCardDTO.getCreateDate());
		callCard.setDateIssue(callCardDTO.getDateIssue());
		callCard.setLibaryCard(new LibraryCard(callCardDTO.getLibaryCardDTO().getId()));
		callCard.setStatus(callCardDTO.getStatus());
		callCard.setUpdateDate(new Date());
		callCard.setUsers(new Users(callCardDTO.getUsersDTO().getId()));
		callCardDAO.delete(callCard);
	}

	public List<CallCardDTO> findAllFinish(CallCardDTO callCardDTO, Paging paging) {
		StringBuilder queryStr = new StringBuilder();
		Map<String,Object> mapParams = new HashedMap();
		List<CallCardDTO> list = new ArrayList<CallCardDTO>();
		for(CallCard callCard : callCardDAO.findAllFinish(queryStr.toString(), mapParams, paging)) {
			CallCardDTO dto = ConvertToDTO.convertCallCardEntityToDTO(callCard);
			list.add(dto);
		}		
		return list;
	}

	public List<CallCardDTO> findAllUnFinish(CallCardDTO callCardDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String,Object> mapParams = new HashedMap();
		if(callCardDTO != null) {
			if(callCardDTO.getLibaryCardDTO() != null) {
				if(callCardDTO.getLibaryCardDTO().getReadersDTO() != null) {
					if(!StringUtils.isEmpty(callCardDTO.getLibaryCardDTO().getReadersDTO().getMssv()) &&callCardDTO.getLibaryCardDTO().getReadersDTO().getMssv() != null ) {
						queryStr.append(" and model.libaryCard.readers.mssv =:mssv ");
						mapParams.put("mssv", callCardDTO.getLibaryCardDTO().getReadersDTO().getMssv());
					}
				}
			}
		}
		List<CallCardDTO> list = new ArrayList<CallCardDTO>();
		for(CallCard callCard : callCardDAO.findAllUnfinish(queryStr.toString(), mapParams, paging)) {
			CallCardDTO dto = ConvertToDTO.convertCallCardEntityToDTO(callCard);
			list.add(dto);
		}		
		return list;
	}

}
