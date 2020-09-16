package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.PublisherDTO;
import com.tampro.service.PublisherService;

@Component
public class PublisherValidator implements Validator{
	@Autowired
	PublisherService publisherService;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return PublisherDTO.class == clazz;
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "msg.required");
		PublisherDTO publisherDTO = (PublisherDTO) target;
		if(!StringUtils.isEmpty(publisherDTO.getName()) && !StringUtils.isEmpty(publisherDTO.getCode())) {
			List<PublisherDTO> list = publisherService.findByProperty("code", publisherDTO.getCode());
			if(publisherDTO.getId() != null &&publisherDTO.getId() != 0) {
				PublisherDTO dto = publisherService.findById(publisherDTO.getId());
				if(!dto.getCode().equals(publisherDTO.getCode())) {
					if(!list.isEmpty()) {
						errors.rejectValue("code", "msg.code.exist");
					}
				}
			}else {
				if(!list.isEmpty()) {
					errors.rejectValue("code", "msg.code.exist");
				}
			}
		}
	}
	

}
