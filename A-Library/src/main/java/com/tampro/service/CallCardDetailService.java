package com.tampro.service;

import java.util.List;

import com.tampro.dto.CallCardDetailDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.UsersDTO;

public interface CallCardDetailService {
	CallCardDetailDTO findById(int id);
	List<CallCardDetailDTO> findByProperty(String property, Object object);
	List<CallCardDetailDTO> findAll(CallCardDetailDTO callCardDetailDTO,Paging paging);
	List<CallCardDetailDTO> findAllFinish(CallCardDetailDTO callCardDetailDTO,Paging paging);
	void save(CallCardDetailDTO callCardDetailDTO, UsersDTO usersDTO) throws Exception;
	void update(CallCardDetailDTO callCardDetailDTO,UsersDTO usersDTO) throws Exception;


}
