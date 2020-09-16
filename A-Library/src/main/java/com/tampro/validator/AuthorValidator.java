package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.AuthorDTO;
import com.tampro.service.AuthorService;

@Component
public class AuthorValidator implements Validator {
	@Autowired
	AuthorService authorService;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return AuthorDTO.class == clazz;
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AuthorDTO authorDTO = (AuthorDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "msg.required");
		if(!StringUtils.isEmpty(authorDTO.getCode()) && !StringUtils.isEmpty(authorDTO.getName())) {
			List<AuthorDTO> authorDTOs =  authorService.findByProperty("code", authorDTO.getCode());
			if(authorDTOs !=null && !authorDTOs.isEmpty()) {
				if(authorDTO.getId() != 0) {
					AuthorDTO dto = authorService.findById(authorDTO.getId());
					if(!dto.getCode().equals(authorDTO.getCode())) {
						if(!authorDTOs.isEmpty()) {						
							errors.rejectValue("code", "msg.code.exist");
						}
					}
				}else {
					if(!authorDTOs.isEmpty()) {
						errors.rejectValue("code", "msg.code.exist");
					}
				}
			}
		}
	}

}
