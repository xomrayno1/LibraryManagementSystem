package com.tampro.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.ProductInfoDTO;
import com.tampro.dto.UsersDTO;
import com.tampro.service.InvoiceService;
import com.tampro.service.ProductInfoService;
import com.tampro.utils.Constant;
import com.tampro.validator.InvoiceValidator;

@Controller
public class GoodsReceiptController {
	
	@InitBinder
	private void Innit(WebDataBinder dataBinder) {
		if(dataBinder.getTarget()== null) {
			return;
		}
		if(dataBinder.getTarget().getClass() == InvoiceDTO.class) {
			dataBinder.setValidator(invoiceValidator);
		}
		SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
	@Autowired
	InvoiceValidator invoiceValidator;
	@Autowired
	InvoiceService goodsReceiptService;
	@Autowired
	ProductInfoService productService;

	@GetMapping(value = {"/goods-receipt/list/","/goods-receipt/list"})
	public String redirect() {
		return "redirect:/goods-receipt/list/1";
	}
	
	@RequestMapping("/goods-receipt/list/{page}")
	public String showInvoiceList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") InvoiceDTO invoiceDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		invoiceDTO.setType(Constant.GOODS_RECEIPT);
		List<InvoiceDTO> dtos  = goodsReceiptService.findAll(invoiceDTO, paging);
		model.addAttribute("listProduct",dtos);
		model.addAttribute("pageInfo" ,paging);	
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR,session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS,session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
 		return "goods-receipt-list";
	}

	@RequestMapping("/goods-receipt/add")
	public String invoiceAdd(Model model) {		
		List<ProductInfoDTO> list = productService.findAll(null, null);
		sortProductInfo(list);
		model.addAttribute("listProduct",list);
		model.addAttribute("submitForm",new InvoiceDTO());
		model.addAttribute("title","Add");
		model.addAttribute("viewOnly",false);
 		return "goods-receipt-action";
	}
	public void sortProductInfo(List<ProductInfoDTO> list) {
		Collections.sort(list, new Comparator<ProductInfoDTO>() {

			public int compare(ProductInfoDTO o1, ProductInfoDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareTo(o2.getName());
			}
		});
	}
	@GetMapping("/goods-receipt/view/{id}")
	public String invoiceView(Model model,@PathVariable("id") int id) {
		InvoiceDTO invoiceDTO =  goodsReceiptService.findById(id);		
		model.addAttribute("submitForm",invoiceDTO);
		model.addAttribute("title","View");
		model.addAttribute("viewOnly",true);
 		return "goods-receipt-action";
	}
	@GetMapping("/goods-receipt/edit/{id}")
	public String invoiceEdit(Model model,@PathVariable("id") int id) {
		InvoiceDTO invoiceDTO =  goodsReceiptService.findById(id);
		List<ProductInfoDTO> list = productService.findAll(null, null);
		sortProductInfo(list);
		model.addAttribute("listProduct",list);
		model.addAttribute("submitForm",invoiceDTO);
		model.addAttribute("title","Edit");
		model.addAttribute("viewOnly",false);
 		return "goods-receipt-action";
	}

	
	@RequestMapping("/goods-receipt/save")
	public String invoiceSave(Model model,@ModelAttribute("submitForm") @Validated InvoiceDTO invoiceDTO,BindingResult result, HttpSession session)  {
		 UsersDTO usersDTO= (UsersDTO)session.getAttribute(Constant.USER_INFO);
		 invoiceDTO.setUsersDTO(usersDTO);
		 invoiceDTO.setType(Constant.GOODS_RECEIPT);
		if(result.hasErrors()) {
			if(invoiceDTO.getId() != 0) {
				model.addAttribute("title","Edit");
			}else {
				model.addAttribute("title","Add");
				
			}
			List<ProductInfoDTO> list = productService.findAll(null, null);
			sortProductInfo(list);
			model.addAttribute("listProduct",list);
			return "goods-receipt-action";
		}
		if(invoiceDTO.getId() != 0) {
			try {
				goodsReceiptService.update(invoiceDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật bị lỗi");
			}
		}else {
			try {
				goodsReceiptService.save(invoiceDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm bị lỗi");
			}
		}
 		return "redirect:/goods-receipt/list/1";
	}
}
