package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.HistoryDAO;
import com.tampro.dto.HistoryDTO;
import com.tampro.dto.Paging;
import com.tampro.entity.History;
import com.tampro.entity.ProductInfo;
import com.tampro.entity.Users;
import com.tampro.service.HistoryService;
import com.tampro.utils.ConvertToDTO;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	HistoryDAO<History> historyDAO;

	public HistoryDTO findById(int id) {
		// TODO Auto-generated method stub
		History history = historyDAO.findById(History.class, id);
		HistoryDTO historyDTO = ConvertToDTO.convertHistoryEntityToDTO(history);
		return historyDTO;
	}

	public List<HistoryDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<HistoryDTO> list = new ArrayList<HistoryDTO>();
		for(History history : historyDAO.findByProperty(property, object)) {
			HistoryDTO historyDTO = ConvertToDTO.convertHistoryEntityToDTO(history);
			list.add(historyDTO);
		}
		return list;
	}

	public List<HistoryDTO> findAll(HistoryDTO historyDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(historyDTO!=null) {
			if( historyDTO.getType() != 0) {
				queryStr.append(" and model.type =:type ");
				mapParams.put("type", historyDTO.getType());
			}
		}
		queryStr.append(" order by model.id desc  ");
		List<HistoryDTO> list = new ArrayList<HistoryDTO>();
		for(History history : historyDAO.findAll(queryStr.toString(), mapParams, paging)) {
			HistoryDTO dto = ConvertToDTO.convertHistoryEntityToDTO(history);
			list.add(dto);
		}
		return list;
	}

	public void save(HistoryDTO historyDTO) throws Exception {
		// TODO Auto-generated method stub
		History history = new History();
		history.setActionName(historyDTO.getActionName());
		history.setActiveFlag(1);
		history.setCreateDate(new Date());
		history.setProductInfo(new ProductInfo(historyDTO.getProductInfoDTO().getId()));
		history.setQuantity(historyDTO.getQuantity());
		history.setUpdateDate(new Date());
		history.setType(historyDTO.getType());
		history.setUsers(new Users(historyDTO.getUsersDTO().getId()));
		historyDAO.save(history);
	}


	
	
	
}
