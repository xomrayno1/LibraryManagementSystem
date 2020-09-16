package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.ReadersDTO;
import com.tampro.service.ReadersService;

@Component
public class ReadersValidator  implements Validator{

	@Autowired
	ReadersService readersService;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ReadersDTO.class == clazz;
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mssv", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameClass", "msg.required");
		ReadersDTO readersDTO = (ReadersDTO) target;
		if(!StringUtils.isEmpty(readersDTO.getName()) && !StringUtils.isEmpty(readersDTO.getMssv())) {
			List<ReadersDTO> list = readersService.findByProperty("mssv", readersDTO.getMssv());
			if(readersDTO.getId() != 0) {
				ReadersDTO dto = readersService.findById(readersDTO.getId());
				if(!dto.getMssv().equals(readersDTO.getMssv())) {
					if(!list.isEmpty()) {
						errors.rejectValue("mssv", "msg.mssv.exist");
					}
				}
			}else {
				if(!list.isEmpty()) {
					errors.rejectValue("mssv", "msg.mssv.exist");
				}
			}
		}
	}
}
