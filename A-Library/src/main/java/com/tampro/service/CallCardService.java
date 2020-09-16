package com.tampro.service;

import java.util.List;

import com.tampro.dto.CallCardDTO;
import com.tampro.dto.Paging;

public interface CallCardService {
	CallCardDTO findById(int id);
	List<CallCardDTO> findByProperty(String property, Object object);
	List<CallCardDTO> findAllFinish(CallCardDTO callCardDTO,Paging paging);
	List<CallCardDTO> findAllUnFinish(CallCardDTO callCardDTO,Paging paging);
	int save(CallCardDTO callCardDTO) throws Exception;
	void update(CallCardDTO callCardDTO) throws Exception;
	void delete(CallCardDTO callCardDTO) throws Exception;
}
