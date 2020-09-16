package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.CategoryDTO;
import com.tampro.service.CategoryService;

@Component
public class CategoryValidator implements Validator {
	@Autowired
	CategoryService categoryService;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return CategoryDTO.class == clazz;
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "msg.required");
		CategoryDTO categoryDTO = (CategoryDTO) target;
		if(!StringUtils.isEmpty(categoryDTO.getName()) && !StringUtils.isEmpty(categoryDTO.getCode())) {
			List<CategoryDTO> list = categoryService.findByProperty("code", categoryDTO.getCode());
			if(categoryDTO.getId()!=null && categoryDTO.getId() != 0) {
				CategoryDTO dto = categoryService.findById(categoryDTO.getId());
				if(!dto.getCode().equals(categoryDTO.getCode())) {
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
