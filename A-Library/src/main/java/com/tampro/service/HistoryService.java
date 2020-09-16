package com.tampro.service;

import java.util.List;

import com.tampro.dto.HistoryDTO;
import com.tampro.dto.Paging;

public interface HistoryService {
	HistoryDTO findById(int id);
	List<HistoryDTO> findByProperty(String property, Object object);
	List<HistoryDTO> findAll(HistoryDTO historyDTO,Paging paging);
	void save(HistoryDTO historyDTO) throws Exception;

}
