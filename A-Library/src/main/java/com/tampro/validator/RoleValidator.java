package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.RoleDTO;
import com.tampro.service.RoleService;

@Component
public class RoleValidator  implements Validator{
	@Autowired
	RoleService roleService;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(RoleDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "msg.required");
		RoleDTO roleDTO = (RoleDTO) target;
		if(roleDTO != null) {
			if(!StringUtils.isEmpty(roleDTO.getName())) {
				List<RoleDTO> list =  roleService.findByProperty("name", roleDTO.getName());
				if(roleDTO.getId() != 0) {
					RoleDTO roleCurrent = roleService.findById(roleDTO.getId());
					if(!roleCurrent.getName().equals(roleDTO.getName())) {
						if(!list.isEmpty()) {
							errors.rejectValue("name", "msg.name.exist");
						}
					}
				}else {
					if(!list.isEmpty()) {
						errors.rejectValue("name", "msg.name.exist");
					}
				}
			}
		}
	}

}
