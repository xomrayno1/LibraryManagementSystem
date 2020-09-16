package com.tampro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping(value = {"/return/list/","/return/list"})
	public String redirectIssue() {
		return "redirect:/return/list/1";
	}
	@RequestMapping(value = "/return/list/{page}")
	public String showCallCard(Model model,HttpSession session,@ModelAttribute("searchForm")CallCardDetailDTO callCardDetailDTO ,@PathVariable("page")int page ) throws Exception {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<CallCardDetailDTO> list  = callCardDetailService.findAllFinish(callCardDetailDTO, paging);
		
		model.addAttribute("listProduct",list);
		model.addAttribute("pageInfo" ,paging);
		return "return-list";
	}
	
	

}
