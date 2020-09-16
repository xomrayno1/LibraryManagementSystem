package com.tampro.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tampro.dto.CallCardDTO;
import com.tampro.dto.CallCardDetailDTO;
import com.tampro.dto.LibraryCardDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.ProductInStockDTO;
import com.tampro.dto.UsersDTO;
import com.tampro.service.CallCardDetailService;
import com.tampro.service.CallCardService;
import com.tampro.service.HistoryService;
import com.tampro.service.LibraryCardService;
import com.tampro.service.ProductInStockService;
import com.tampro.service.ProductInfoService;
import com.tampro.service.UserService;
import com.tampro.utils.Constant;
import com.tampro.validator.LoginValidator;

@Controller
public class CallCardController {
	@Autowired
	LoginValidator loginValidator;
	@Autowired
	UserService  userService;
	@Autowired
	ProductInfoService productInfoService;
	@Autowired
	ProductInStockService productInStockService;
	@Autowired
	LibraryCardService libraryCardService;
	@Autowired
	CallCardService callCardService;
	@Autowired
	CallCardDetailService callCardDetailService;
	@Autowired
	HistoryService historyService;
	
	@InitBinder
	public void initParam(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		if(dataBinder.getTarget().getClass() == UsersDTO.class) {
			dataBinder.setValidator(loginValidator);
		}
	}
	@GetMapping(value = {"/","/call-card/add","/call-card/add"})
	public String redirect() {
		return "redirect:/call-card/add/1";
	}
	
	@RequestMapping(value = {"/call-card/add/{page}"})
	public String home(Model model ,@PathVariable("page") int page ,@ModelAttribute("searchForm") ProductInStockDTO productInStockDTO,HttpSession session ) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<ProductInStockDTO> dtos  = productInStockService.findAll(productInStockDTO, paging);
		model.addAttribute("listProduct",dtos);
		model.addAttribute("pageInfo" ,paging);
		if(session.getAttribute(Constant.MSG_SUCCESS) !=null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		return "call-card";
	}
	@GetMapping(value = "/call-card/session/add/{id}")
	public String addCallCard(Model model, @PathVariable("id") int id,HttpSession session ) throws ParseException {
		UsersDTO usersDTO = (UsersDTO) session.getAttribute(Constant.USER_INFO);
		LibraryCardDTO libaryCardDTO = libraryCardService.findById(id);
		if(!check(libaryCardDTO)) {// check xem thẻ thư viện đã có đang mượn chưa , nếu đang mượn thì ko cho mượn tiếp
			session.setAttribute(Constant.MSG_ERROR, "Bạn có 1 phiếu mượn chưa hoàn thành");
			return "redirect:/library-card/list/1";
		}else {
			CallCardDTO callCardDTO = (CallCardDTO) session.getAttribute(Constant.CALL_CARD);
			if(callCardDTO != null) {
				if(callCardDTO.getLibaryCardDTO().getId() ==  id) {
					return "redirect:/call-card/add/1";
				}
				callCardDTO.setCardDetailDTOs(null);
			}else {
				callCardDTO = new CallCardDTO();
			}
			callCardDTO.setDateIssue(new Date());
			callCardDTO.setLibaryCardDTO(libaryCardDTO);
			callCardDTO.setStatus(Constant.UNFINISH);
			callCardDTO.setUsersDTO(usersDTO);
			session.setAttribute(Constant.CALL_CARD,callCardDTO);
		}		
		return "redirect:/call-card/add/1";
	}
	public boolean check(LibraryCardDTO libaryCardDTO) {
		List<CallCardDTO> list = callCardService.findByProperty("libaryCard.id", libaryCardDTO.getId());	
		for(CallCardDTO dto : list) {
			if(dto.getStatus() == Constant.UNFINISH) {
				return false;
			}
		}
		return true;	
	}
	@GetMapping(value = "/call-card/session/remove")
	public String removeCallCard(Model model,HttpSession session ) {
		session.removeAttribute(Constant.CALL_CARD);
		return "redirect:/call-card/add/1";
	}
	@GetMapping(value = "/call-card-detail/session/remove/{id}")
	public String removeCallCardDetail(Model model,HttpSession session,@PathVariable("id") int id ) {
		CallCardDTO callCardDTO = (CallCardDTO) session.getAttribute(Constant.CALL_CARD);
		for(CallCardDetailDTO detailDTO : callCardDTO.getCardDetailDTOs()) {
			if(detailDTO.getProductInfoDTO().getId() == id) {
				callCardDTO.getCardDetailDTOs().remove(detailDTO);
				break;
			}
		}
		return "redirect:/call-card/check-out";
	}
	@GetMapping(value = "/call-card-detail/session/edit/{id}")
	public String editCallCardDetail(Model model,HttpSession session,@PathVariable("id") int id,@RequestParam("dueDate") String date) throws ParseException {
		System.out.println(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dueDate = dateFormat.parse(date);
		CallCardDTO callCardDTO = (CallCardDTO) session.getAttribute(Constant.CALL_CARD);
		for(CallCardDetailDTO detailDTO : callCardDTO.getCardDetailDTOs()) {
			if(detailDTO.getProductInfoDTO().getId() == id) {
				detailDTO.setDueDate(dueDate);
				break;
			}
		}
		return "redirect:/call-card/check-out";
	}
	@GetMapping(value = "/call-card/session/reset")
	public String resetCallCard(Model model,HttpSession session ) {
		CallCardDTO callCardDTO =  (CallCardDTO) session.getAttribute(Constant.CALL_CARD);
		callCardDTO.setCardDetailDTOs(null);
		session.setAttribute(Constant.CALL_CARD, callCardDTO);
		return "redirect:/call-card/add/1";
	}
	@GetMapping(value = "/callcard-detail/session/add/{id}")
	public String addCallCardDetail(Model model, @PathVariable("id") int id,HttpSession session ) {
		CallCardDTO callCardDTO = (CallCardDTO) session.getAttribute(Constant.CALL_CARD);
		ProductInStockDTO inStockDTO = productInStockService.findById(id);
		if(inStockDTO != null && inStockDTO.getQuantity() > 0) {
			if(callCardDTO == null) {
				return "redirect:/library-card/list/1";
			}else {
				if(callCardDTO.getCardDetailDTOs() != null) {
					boolean flag = true;
					for(CallCardDetailDTO detailDTO :callCardDTO.getCardDetailDTOs() ) {
						if(detailDTO.getProductInfoDTO().getId() ==  inStockDTO.getProductInfoDTO().getId()) {
							flag = false;
						}
					}
					if(flag) {
						CallCardDetailDTO callCardDetailDTO = new CallCardDetailDTO();
						callCardDetailDTO.setProductInfoDTO(inStockDTO.getProductInfoDTO());
						Date dueDate = addDate(callCardDTO.getDateIssue(), Constant.DATE_ISSUE);
						callCardDetailDTO.setDueDate(dueDate);
						callCardDetailDTO.setStatus(Constant.BORROW);
						callCardDTO.getCardDetailDTOs().add(callCardDetailDTO);
					}
				}else {
					Set<CallCardDetailDTO> callCardDetailDTOs = new HashSet<CallCardDetailDTO>();
					CallCardDetailDTO callCardDetailDTO = new CallCardDetailDTO();
					callCardDetailDTO.setProductInfoDTO(inStockDTO.getProductInfoDTO());
					Date dueDate = addDate(callCardDTO.getDateIssue(), Constant.DATE_ISSUE);
					callCardDetailDTO.setDueDate(dueDate);
					callCardDetailDTO.setStatus(Constant.BORROW);
					callCardDetailDTOs.add(callCardDetailDTO);
					callCardDTO.setCardDetailDTOs(callCardDetailDTOs);
				}
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công !");
				session.setAttribute(Constant.CALL_CARD, callCardDTO);
			}
		}else {
			session.setAttribute(Constant.MSG_ERROR, "Sách trong kho đã hết !");
		}
		return "redirect:/call-card/add/1";
	}
	@GetMapping(value = "/call-card/save")
	public String addCallCard(Model model,HttpSession session ) throws Exception {
		CallCardDTO callCardDTO =  (CallCardDTO) session.getAttribute(Constant.CALL_CARD);
		UsersDTO usersDTO = (UsersDTO) session.getAttribute(Constant.USER_INFO);
		int id = callCardService.save(callCardDTO);
		for(CallCardDetailDTO cardDetailDTO  : callCardDTO.getCardDetailDTOs()) {
			cardDetailDTO.setCallCardId(id);
			callCardDetailService.save(cardDetailDTO,usersDTO);
		}
		session.removeAttribute(Constant.CALL_CARD);
		return "redirect:/issue/list/1";
	}
	
	public Date addDate(Date date, int numberDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, numberDay);
		return calendar.getTime();
	}

	@GetMapping(value = {"/call-card/check-out"})
	public String checkOut(Model model) {
	
		return "check-out";
	}
	

}


