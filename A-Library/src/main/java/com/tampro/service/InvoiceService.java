package com.tampro.service;

import java.util.List;

import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.Paging;

public interface InvoiceService {
	InvoiceDTO findById(int id);
	List<InvoiceDTO> findByProperty(String property, Object object);
	List<InvoiceDTO> findAll(InvoiceDTO invoiceDTO,Paging paging);
	void save(InvoiceDTO invoiceDTO) throws Exception;
	void update(InvoiceDTO invoiceDTO) throws Exception;
}
