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

import com.tampro.dto.AuthorDTO;
import com.tampro.dto.Paging;
import com.tampro.service.AuthorService;
import com.tampro.utils.Constant;
import com.tampro.validator.AuthorValidator;

@Controller
public class AuthorController {
	@InitBinder
	public void webInitBinder(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return ;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		dataBinder.registerCustomEditor(Date.class,new CustomDateEditor(simpleDateFormat, true));
		if(dataBinder.getTarget().getClass() == AuthorDTO.class) {
			dataBinder.setValidator(authorValidator);
		}
	}
	@Autowired
	AuthorService authorService;
	@Autowired
	AuthorValidator authorValidator;
	
	
	@RequestMapping(value = {"/author/list","/author/list/"})
	public String redirect() {
		return "redirect:/author/list/1";
	}
	
	@RequestMapping("/author/list/{page}")
	public String showAuthorList(Model model,@ModelAttribute("searchForm") AuthorDTO authorDTO,@PathVariable("page") int page,HttpSession session) {
		Paging paging = new Paging(5);
		paging.setIndexPage(page);
		List<AuthorDTO> listAuthorDTO = authorService.findAll(authorDTO, paging);
		model.addAttribute("listProduct",listAuthorDTO);
		model.addAttribute("pageInfo",paging);
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS,session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if(session.getAttribute(Constant.MSG_ERROR) !=null) {
			model.addAttribute(Constant.MSG_ERROR,session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		return "author-list";
	}
	@GetMapping("/author/add")
	public String authorAdd(Model model) {
		model.addAttribute("submitForm",new AuthorDTO());
		model.addAttribute("title","Add");
		model.addAttribute("viewOnly",false);
		return "author-action";
	}
	@PostMapping("/author/delete")
	public String authorAdd(Model model,@RequestParam("id") int id,HttpSession session) {
		AuthorDTO authorDTO = authorService.findById(id);
		try {
			authorService.delete(authorDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
		return "redirect:/author/list/1";
	}
	@GetMapping("/author/edit/{id}")
	public String authorEdit(Model model,@PathVariable("id")int id) {
		AuthorDTO authorDTO = authorService.findById(id);
		model.addAttribute("submitForm",authorDTO);
		model.addAttribute("title","Edit");
		model.addAttribute("viewOnly",false);
		return "author-action";
	}
	@GetMapping("/author/view/{id}")
	public String authorView(Model model,@PathVariable("id")int id) {
		AuthorDTO authorDTO = authorService.findById(id);
		model.addAttribute("submitForm",authorDTO);
		model.addAttribute("title","View");
		model.addAttribute("viewOnly",true);
		return "author-action";
	}
	@PostMapping("/author/save")
	public String authorView(Model model,@ModelAttribute("submitForm") @Validated AuthorDTO authorDTO, BindingResult result ,HttpSession session) {
		if(result.hasErrors()) {
			if(authorDTO.getId() != 0) {
				model.addAttribute("title","Edit");
			}else {
				model.addAttribute("title","Add");
			}
			model.addAttribute("viewOnly",false);
			return "author-action";
		}
		if(authorDTO.getId() != null && authorDTO.getId() !=0) {
			try {
				authorService.update(authorDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
			}
		}else {
			try {
				authorService.save(authorDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm thất bại");
			}
		}
		
	
		return "redirect:/author/list/1";
	}
}
