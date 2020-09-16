package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.LibraryCardDAO;
import com.tampro.dto.LibraryCardDTO;
import com.tampro.dto.Paging;
import com.tampro.entity.LibraryCard;
import com.tampro.entity.Readers;
import com.tampro.service.LibraryCardService;
import com.tampro.utils.ConvertToDTO;

@Service
public class LibraryCardServiceImpl implements LibraryCardService {

	@Autowired
	LibraryCardDAO<LibraryCard> libraryCardDAO;
	
	
	public LibraryCardDTO findById(int id) {
		// TODO Auto-generated method stub
		LibraryCard libraryCard = libraryCardDAO.findById(LibraryCard.class, id);
		LibraryCardDTO libraryCardDTO = ConvertToDTO.convertLibraryCardEntityToDTO(libraryCard);
		return libraryCardDTO;
	}

	public List<LibraryCardDTO> findByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<LibraryCardDTO> libraryCardDTOs = new ArrayList<LibraryCardDTO>();
		for(LibraryCard card : libraryCardDAO.findByProperty(property, object)) {
			LibraryCardDTO libraryCardDTO = ConvertToDTO.convertLibraryCardEntityToDTO(card);
			libraryCardDTOs.add(libraryCardDTO);
		}
		return libraryCardDTOs;
	}

	public List<LibraryCardDTO> findAll(LibraryCardDTO libraryCardDTO, Paging paging) {
		// TODO Auto-generated method stub
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<String, Object>();
		if(libraryCardDTO!=null) {
			if(libraryCardDTO.getReadersDTO() != null) {
				if(libraryCardDTO.getReadersDTO().getMssv() != null && !StringUtils.isEmpty(libraryCardDTO.getReadersDTO().getMssv())) {
					queryStr.append(" and model.readers.mssv =:mssv");
					mapParams.put("mssv",libraryCardDTO.getReadersDTO().getMssv() );
				}
				if(libraryCardDTO.getReadersDTO().getName() != null && !StringUtils.isEmpty(libraryCardDTO.getReadersDTO().getName())) {
					queryStr.append(" and model.readers.name like  :name");
					mapParams.put("name","%" + libraryCardDTO.getReadersDTO().getName()+"%");
				}
			}
		}
		List<LibraryCardDTO> libraryCardDTOs = new ArrayList<LibraryCardDTO>();
		for(LibraryCard card : libraryCardDAO.findAll(queryStr.toString(), mapParams, paging)) {
			LibraryCardDTO dto = ConvertToDTO.convertLibraryCardEntityToDTO(card);
			libraryCardDTOs.add(dto);
		}
		return libraryCardDTOs;
	}

	public void save(LibraryCardDTO libraryCardDTO) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===save librarycard===");
		LibraryCard libraryCard = new LibraryCard();
		libraryCard.setActiveFlag(1);
		libraryCard.setCreateDate(new Date());
		libraryCard.setDescription(libraryCardDTO.getDescription());
		libraryCard.setEndDay(libraryCardDTO.getEndDay());
		libraryCard.setReaders(new Readers(libraryCardDTO.getReadersDTO().getId()));
		libraryCard.setStartDay(libraryCardDTO.getStartDay());
		libraryCard.setUpdateDate(new Date());
		libraryCardDAO.save(libraryCard);
	}

	public void update(LibraryCardDTO libraryCardDTO) throws Exception {
		// TODO Auto-generated method stub
		LibraryCard libraryCard = new LibraryCard();
		libraryCard.setActiveFlag(libraryCardDTO.getActiveFlag());
		libraryCard.setId(libraryCardDTO.getId());
		libraryCard.setCreateDate(libraryCardDTO.getCreateDate());
		libraryCard.setDescription(libraryCardDTO.getDescription());
		libraryCard.setEndDay(libraryCardDTO.getEndDay());
		libraryCard.setReaders(new Readers(libraryCardDTO.getReadersDTO().getId()));
		libraryCard.setStartDay(libraryCardDTO.getStartDay());
		libraryCard.setUpdateDate(new Date());
		libraryCardDAO.update(libraryCard);
	}

	public void delete(LibraryCardDTO libraryCardDTO) throws Exception {
		// TODO Auto-generated method stub
		LibraryCard libraryCard = new LibraryCard();
		libraryCard.setActiveFlag(0);
		libraryCard.setCreateDate(libraryCardDTO.getCreateDate());
		libraryCard.setDescription(libraryCardDTO.getDescription());
		libraryCard.setEndDay(libraryCardDTO.getEndDay());
		libraryCard.setStartDay(libraryCardDTO.getStartDay());
		libraryCard.setUpdateDate(new Date());;
		libraryCard.setId(libraryCardDTO.getId());
		libraryCardDAO.delete(libraryCard);
	}

}
