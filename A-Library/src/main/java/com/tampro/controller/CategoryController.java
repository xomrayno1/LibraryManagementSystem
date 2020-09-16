package com.tampro.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
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

import com.tampro.dto.CategoryDTO;
import com.tampro.dto.Paging;
import com.tampro.service.CategoryService;
import com.tampro.utils.Constant;
import com.tampro.validator.CategoryValidator;

@Controller
public class CategoryController {	
	@InitBinder
	public void webInitBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return ;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:yy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == CategoryDTO.class) {
			binder.setValidator(categoryValidator);
		}
	}

	@Autowired
	CategoryValidator categoryValidator;
	@Autowired
	CategoryService categoryService;

	@GetMapping(value = {"/category/list/","/category/list"})
	public String redirect() {
		return "redirect:/category/list/1";
	}
	
	@RequestMapping("/category/list/{page}")
	public String showCategoryList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") CategoryDTO categoryDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<CategoryDTO> dtos  = categoryService.findAll(categoryDTO, paging);
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
 		return "category-list";
	}
	@GetMapping("/category/add")
	public String categoryAdd(Model model) {
		model.addAttribute("submitForm",new CategoryDTO());
		model.addAttribute("title","Add");
		model.addAttribute("viewOnly",false);
 		return "category-action";
	}
	@GetMapping("/category/view/{id}")
	public String categoryView(Model model,@PathVariable("id") int id) {
		CategoryDTO categoryDTO =  categoryService.findById(id);
		model.addAttribute("submitForm",categoryDTO);
		model.addAttribute("title","View");
		model.addAttribute("viewOnly",true);
 		return "category-action";
	}
	@GetMapping("/category/edit/{id}")
	public String categoryEdit(Model model,@PathVariable("id") int id) {
		CategoryDTO categoryDTO =  categoryService.findById(id);
		model.addAttribute("submitForm",categoryDTO);
		model.addAttribute("title","Edit");
		model.addAttribute("viewOnly",false);
 		return "category-action";
	}
	@PostMapping("/category/delete")
	public String categoryDelete(Model model,@RequestParam("id") int id,HttpSession session) {
		CategoryDTO categoryDTO =  categoryService.findById(id);
		try {
			categoryService.delete(categoryDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
 		return "redirect:/category/list/1";
	}
	@PostMapping("/category/save")
	public String categorySave(Model model,@ModelAttribute("submitForm") @Validated CategoryDTO categoryDTO,BindingResult result, HttpSession session)  {
		
		if(result.hasErrors()) {
			if(categoryDTO.getId()!=null && categoryDTO.getId() != 0) {
				model.addAttribute("title","Edit");
			}else {
				model.addAttribute("title","Add");
			}
			return "category-action";
		}
		if(categoryDTO.getId() != null && categoryDTO.getId() != 0) {
			try {
				categoryService.update(categoryDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật bị lỗi");
			}
		}else {
			try {
				categoryService.save(categoryDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm bị lỗi");
			}
		}
 		return "redirect:/category/list/1";
	}
}
