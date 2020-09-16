package com.tampro.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tampro.dto.InvoiceDTO;

@Component
public class InvoiceValidator  implements Validator{

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(InvoiceDTO.class);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		InvoiceDTO invoiceDTO = (InvoiceDTO) target;
		if(invoiceDTO.getQuantity() <= 0) {
			errors.rejectValue("quantity", "msg.wrong");
		}
 		
	}
	

}
