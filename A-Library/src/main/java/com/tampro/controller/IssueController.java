package com.tampro.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tampro.dto.CallCardDTO;
import com.tampro.dto.CallCardDetailDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.UsersDTO;
import com.tampro.service.CallCardDetailService;
import com.tampro.service.CallCardService;
import com.tampro.service.HistoryService;
import com.tampro.service.ProductInStockService;
import com.tampro.utils.Constant;

@Controller
public class IssueController {
	
	@Autowired
	CallCardService	callCardService;
	@Autowired
	CallCardDetailService callCardDetailService;
	@Autowired
	HistoryService historyService;
	@Autowired
	ProductInStockService productInStockService;
	@GetMapping(value = {"/issue/list/","/issue/list"})
	public String redirectIssue() {
		return "redirect:/issue/list/1";
	}
	@RequestMapping(value = "/issue/list/{page}")
	public String showCallCard(Model model,HttpSession session,@ModelAttribute("searchForm")CallCardDTO callCardDTO ,@PathVariable("page")int page ) throws Exception {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<CallCardDTO> list  = callCardService.findAllUnFinish(callCardDTO, paging);
		model.addAttribute("listProduct",list);
		model.addAttribute("pageInfo" ,paging);
		return "issue-list";
	}
	@GetMapping("/issue/edit/{id}")
	public String callCardView(Model model,@PathVariable("id") int id) throws Exception {
		CallCardDTO callCardDTO =  callCardService.findById(id);
		if(!checkStatusCallCard(callCardDTO)) {
			model.addAttribute("callCard",callCardDTO);
		}else {
			callCardDTO.setStatus(Constant.FINISH);
			callCardService.update(callCardDTO);
		}
 		return "issue-action";
	}
	@GetMapping("/issue/return/{id}")
	public String returnCardViewDetail(Model model,@PathVariable("id") int id,HttpSession session) throws Exception {
		CallCardDetailDTO callCardDetailDTO = callCardDetailService.findById(id);
		UsersDTO usersDTO = (UsersDTO) session.getAttribute(Constant.USER_INFO);
		callCardDetailDTO.setReturnDate(new Date());
		callCardDetailDTO.setStatus(Constant.RETURN);
		callCardDetailService.update(callCardDetailDTO,usersDTO);
		int idCallCard = callCardDetailDTO.getCallCardId();
 		return callCardView(model, idCallCard);
	}
	public boolean checkStatusCallCard(CallCardDTO callCardDTO) {
		for(CallCardDetailDTO dto : callCardDTO.getCardDetailDTOs()) {
			if(dto.getStatus() == Constant.BORROW) {
				return false;
			}
		}
		return true;
		
	}
}
