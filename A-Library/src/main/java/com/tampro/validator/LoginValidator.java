package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.UsersDTO;
import com.tampro.service.UserService;



@Component
public class LoginValidator  implements Validator{
	@Autowired
	UserService userService;

	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == UsersDTO.class;
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UsersDTO usersDTO = (UsersDTO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "msg.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "msg.required");
		if(!StringUtils.isEmpty(usersDTO.getUsername()) && !StringUtils.isEmpty(usersDTO.getPassword())) {
			List<UsersDTO> dtos = userService.findByProperty("username", usersDTO.getUsername());
			if(!dtos.isEmpty() && usersDTO != null) {
				if(!usersDTO.getPassword().equals(dtos.get(0).getPassword())) {
					errors.rejectValue("password", "msg.wrong");
				}
			}else {
				errors.rejectValue("username", "msg.wrong");
				errors.rejectValue("password", "msg.wrong");
			}
		}
	}

	
}
