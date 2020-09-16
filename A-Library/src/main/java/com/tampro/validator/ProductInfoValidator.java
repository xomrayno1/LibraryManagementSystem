package com.tampro.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tampro.dto.ProductInfoDTO;
import com.tampro.service.ProductInfoService;

@Component
public class ProductInfoValidator  implements Validator{
	@Autowired
	ProductInfoService productInfoService;

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ProductInfoDTO.class == clazz;
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "msg.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "msg.required");
		ProductInfoDTO productInfoDTO = (ProductInfoDTO) target;
		if(!StringUtils.isEmpty(productInfoDTO.getName()) && !StringUtils.isEmpty(productInfoDTO.getCode())) {
			List<ProductInfoDTO> list = productInfoService.findByProperty("code", productInfoDTO.getCode());
			if(productInfoDTO.getId() != 0) {
				ProductInfoDTO dto = productInfoService.findById(productInfoDTO.getId());
				if(!dto.getCode().equals(productInfoDTO.getCode())) {
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
		if(productInfoDTO.getImgUrl().isEmpty()) {
			if(productInfoDTO.getMultipartFile().getOriginalFilename().isEmpty()) {
				errors.rejectValue("imgUrl", "msg.required");
			}
		}
		
		
		if(productInfoDTO.getCateId() == 0) {
			errors.rejectValue("cateId", "msg.required");
		}
		if(productInfoDTO.getPublisherId() == 0 ) {
			errors.rejectValue("publisherId", "msg.required");
		}
		if(productInfoDTO.getAuthorId() == 0) {
			errors.rejectValue("authorId", "msg.required");
		}
	}

}
