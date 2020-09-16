package com.tampro.controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tampro.dto.Paging;
import com.tampro.dto.PublisherDTO;
import com.tampro.service.PublisherService;
import com.tampro.utils.Constant;
import com.tampro.validator.PublisherValidator;

@Controller
public class PublisherController {
	@InitBinder
	public void webInitBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return ;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:yy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == PublisherDTO.class) {
			binder.setValidator(publisherValidator);
		}
	}

	@Autowired
	PublisherValidator publisherValidator;
	@Autowired
	PublisherService publisherService;

	@GetMapping(value = {"/publisher/list/","/publisher/list"})
	public String redirect() {
		return "redirect:/publisher/list/1";
	}
	
	@RequestMapping("/publisher/list/{page}")
	public String showCategoryList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") PublisherDTO publisherDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<PublisherDTO> dtos  = publisherService.findAll(publisherDTO, paging);
		model.addAttribute("listProduct",dtos);
		model.addAttribute("pageInfo" ,paging);
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS,session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR,session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
 		return "publisher-list";
	}
	@GetMapping("/publisher/add")
	public String categoryAdd(Model model) {
		model.addAttribute("submitForm",new PublisherDTO());
		model.addAttribute("title","Add");
		model.addAttribute("viewOnly",false);
 		return "publisher-action";
	}
	@GetMapping("/publisher/view/{id}")
	public String categoryView(Model model,@PathVariable("id") int id) {
		PublisherDTO publisherDTO =  publisherService.findById(id);
		model.addAttribute("submitForm",publisherDTO);
		model.addAttribute("title","View");
		model.addAttribute("viewOnly",true);
 		return "publisher-action";
	}
	@GetMapping("/publisher/edit/{id}")
	public String categoryEdit(Model model,@PathVariable("id") int id) {
		PublisherDTO publisherDTO =  publisherService.findById(id);
		model.addAttribute("submitForm",publisherDTO);
		model.addAttribute("title","Edit");
		model.addAttribute("viewOnly",false);
 		return "publisher-action";
	}
	@PostMapping("/publisher/delete")
	public String categoryDelete(Model model,@RequestParam("id") int id,HttpSession session) {
		PublisherDTO publisherDTO =  publisherService.findById(id);
		try {
			publisherService.delete(publisherDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
 		return "redirect:/publisher/list/1";
	}
	@PostMapping("/publisher/save")
	public String categorySave(Model model,@ModelAttribute("submitForm") @Validated PublisherDTO publisherDTO,BindingResult result, HttpSession session)  {
		
		if(result.hasErrors()) {
			if(publisherDTO.getId()!=null && publisherDTO.getId() != 0) {
				model.addAttribute("title","Edit");
			}else {
				model.addAttribute("title","Add");
			}
			return "publisher-action";
		}
		if( publisherDTO.getId() != null && publisherDTO.getId() != 0) {
			try {
				publisherService.update(publisherDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật bị lỗi");
			}
		}else {
			try {
				publisherService.save(publisherDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm bị lỗi");
			}
		}
 		return "redirect:/publisher/list/1";
	}

}
