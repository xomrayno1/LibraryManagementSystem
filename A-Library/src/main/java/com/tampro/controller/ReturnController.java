package com.tampro.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tampro.dto.CallCardDetailDTO;
import com.tampro.dto.Paging;
import com.tampro.service.CallCardDetailService;
import com.tampro.service.CallCardService;

@Controller
public class ReturnController {
	@Autowired
	CallCardService	callCardService;
	@Autowired
	CallCardDetailService callCardDetailService;
	
	@InitBinder
	public void binder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping(value = {"/return/list/","/return/list"})
	public String redirectIssue() {
		return "redirect:/return/list/1";
	}
	@RequestMapping(value = "/return/list/{page}")
	public String showCallCard(Model model,HttpSession session,@ModelAttribute("searchForm")CallCardDetailDTO callCardDetailDTO ,@PathVariable("page")int page ) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<CallCardDetailDTO> list  = callCardDetailService.findAllFinish(callCardDetailDTO, paging);
		model.addAttribute("listProduct",list);
		model.addAttribute("pageInfo" ,paging);
		return "return-list";
	}
	
	

}
