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
public class UserValidator  implements Validator{

	@Autowired
	UserService userService;
	
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz.isAssignableFrom(UsersDTO.class);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "msg.required");
		UsersDTO usersDTO = (UsersDTO) target;
		if(usersDTO != null) {
			if(!StringUtils.isEmpty(usersDTO.getUsername())) {
				List<UsersDTO>  list  =	userService.findByProperty("username", usersDTO.getUsername());
				if(usersDTO.getId() != 0) {
					UsersDTO userDTOCurrent = userService.findById(usersDTO.getId());
					if(!userDTOCurrent.getUsername().equals(usersDTO.getUsername())) {
						if(!list.isEmpty()) {
							errors.rejectValue("username", "msg.username.exist");
						}
					}
				}else {
					if(!list.isEmpty()) {
						errors.rejectValue("username", "msg.username.exist");
					}
				}
			}
		}
		
	}

}
