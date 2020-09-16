package com.tampro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tampro.dto.HistoryDTO;
import com.tampro.dto.Paging;
import com.tampro.service.HistoryService;
import com.tampro.utils.Constant;

@Controller
public class HistoryController {

	@Autowired
	HistoryService historyService;

	@GetMapping(value = {"/history/list/","/history/list"})
	public String redirect() {
		return "redirect:/history/list/1";
	}
	
	@RequestMapping("/history/list/{page}")
	public String showHistoryList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") HistoryDTO historyDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<HistoryDTO> dtos  = historyService.findAll(historyDTO, paging);
		model.addAttribute("listProduct",dtos);
		model.addAttribute("pageInfo" ,paging);
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(Constant.GOODS_RECEIPT, "Nhập hàng");
		map.put(Constant.GOODS_ISSUE, "Xuất hàng");
		map.put(Constant.BORROW, "Mượn");
		map.put(Constant.RETURN, "Trả");
		model.addAttribute("map",map);
 		return "history-list";
	}


}
