package com.tampro.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.LibraryCardDTO;
import com.tampro.service.LibraryCardService;

@Component
public class LibraryCardValidator implements Validator {

	@Autowired
	LibraryCardService libraryCardService;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return LibraryCardDTO.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		LibraryCardDTO libraryCardDTO = (LibraryCardDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDay", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDay", "msg.required");
		if(libraryCardDTO != null) {
			if(!StringUtils.isEmpty(libraryCardDTO.getStartDay()) && !StringUtils.isEmpty(libraryCardDTO.getEndDay())) {
				if(libraryCardDTO.getStartDay().after(libraryCardDTO.getEndDay())) {
					errors.rejectValue("startDay", "msg.wrong.date");
				}
			}
		}
	}	

}
