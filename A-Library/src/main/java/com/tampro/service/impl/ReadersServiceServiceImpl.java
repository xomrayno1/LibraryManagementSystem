package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.ReadersDAO;
import com.tampro.dto.Paging;
import com.tampro.dto.ReadersDTO;
import com.tampro.entity.Readers;
import com.tampro.service.ReadersService;
import com.tampro.utils.ConvertToDTO;

@Service
public class ReadersServiceServiceImpl  implements ReadersService{

	@Autowired
	ReadersDAO<Readers> readersDAO;
	
	public ReadersDTO findById(int id) {
		Readers readers = readersDAO.findById(Readers.class, id);
		ReadersDTO readersDTO = ConvertToDTO.convertReadersEntityToDTO(readers);
		return readersDTO;
	}

	public List<ReadersDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<ReadersDTO> readersDTOs = new ArrayList<ReadersDTO>();
		for(Readers readers : readersDAO.findByProperty(property, object)) {
			ReadersDTO readersDTO = ConvertToDTO.convertReadersEntityToDTO(readers);
			readersDTOs.add(readersDTO);
		}
		return readersDTOs;
	}

	public List<ReadersDTO> findAll(ReadersDTO readersDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String,Object> mapParams = new HashMap<String, Object>();
		StringBuilder queryStr = new StringBuilder("");
		if(readersDTO!= null) {
			if(readersDTO.getName() != null && !StringUtils.isEmpty(readersDTO.getName())) {
				queryStr.append(" and model.name like :name");
				mapParams.put("name", "%"+readersDTO.getName()+"%");
			}
			if(readersDTO.getMssv() != null && !StringUtils.isEmpty(readersDTO.getMssv())) {
				queryStr.append("and  model.mssv =:mssv");
				mapParams.put("mssv",readersDTO.getMssv());
			}
			if(readersDTO.getNameClass() != null && !StringUtils.isEmpty(readersDTO.getNameClass())) {
				queryStr.append(" and model.nameClass =:nameClass");
				mapParams.put("nameClass",readersDTO.getNameClass());
			}
		}
		List<ReadersDTO> readersDTOs = new ArrayList<ReadersDTO>();
		for(Readers readers : readersDAO.findAll(queryStr.toString(), mapParams, paging)) {
			ReadersDTO dto = ConvertToDTO.convertReadersEntityToDTO(readers);
			readersDTOs.add(dto);
		}
		return readersDTOs;
	}

	public void save(ReadersDTO readersDTO) throws Exception {
		// TODO Auto-generated method stub
		Readers readers = new Readers();
		readers.setActiveFlag(1);
		readers.setAddress(readersDTO.getAddress());
		readers.setCreateDate(new Date());
		readers.setMssv(readersDTO.getMssv());
		readers.setName(readersDTO.getName());
		readers.setNameClass(readersDTO.getNameClass());
		readers.setPhone(readersDTO.getPhone());
		readers.setUpdateDate(new Date());
		readersDAO.save(readers);
	}

	public void update(ReadersDTO readersDTO) throws Exception {
		// TODO Auto-generated method stub
		Readers readers = new Readers();
		readers.setActiveFlag(readersDTO.getActiveFlag());
		readers.setAddress(readersDTO.getAddress());
		readers.setCreateDate(new Date());
		readers.setId(readersDTO.getId());
		readers.setMssv(readersDTO.getMssv());
		readers.setName(readersDTO.getName());
		readers.setNameClass(readersDTO.getNameClass());
		readers.setPhone(readersDTO.getPhone());
		readers.setUpdateDate(readersDTO.getUpdateDate());
		readersDAO.update(readers);
	}

	public void delete(ReadersDTO readersDTO) throws Exception {
		// TODO Auto-generated method stub
		Readers readers = new Readers();
		readers.setActiveFlag(0);
		readers.setAddress(readersDTO.getAddress());
		readers.setCreateDate(readersDTO.getCreateDate());
		readers.setId(readersDTO.getId());
		readers.setMssv(readersDTO.getMssv());
		readers.setName(readersDTO.getName());
		readers.setNameClass(readersDTO.getNameClass());
		readers.setPhone(readersDTO.getPhone());
		readers.setUpdateDate(new Date());
		readersDAO.delete(readers);
	}

}
