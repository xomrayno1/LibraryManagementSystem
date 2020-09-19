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
import com.tampro.dto.RoleDTO;
import com.tampro.service.RoleService;
import com.tampro.utils.Constant;
import com.tampro.validator.RoleValidator;

@Controller
public class RoleController {
	@Autowired
	RoleService roleService;	
	@Autowired
	RoleValidator roleValidator;

	@InitBinder
	public void initBinber(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:yy");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(dataBinder.getTarget().getClass() == RoleDTO.class) {
			dataBinder.setValidator(roleValidator);
		}
	}

	@GetMapping(value = {"/role/list/","/role/list"})
	public String redirect() {
		return "redirect:/role/list/1";
	}
	
	@RequestMapping("/role/list/{page}")
	public String showRoleList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") RoleDTO roleDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<RoleDTO> dtos  = roleService.findAll(roleDTO, paging);
		List<RoleDTO> roles = roleService.findAll(null, null);
		model.addAttribute("roles", roles);
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
 		return "role-list";
	}
	@GetMapping("/role/add")
	public String roleAdd(Model model) {
		model.addAttribute("submitForm",new RoleDTO());
		model.addAttribute("title","Add");
		model.addAttribute("viewOnly",false);
 		return "role-action";
	}
	@GetMapping("/role/view/{id}")
	public String roleView(Model model,@PathVariable("id") int id) {
		RoleDTO roleDTO =  roleService.findById(id);
		model.addAttribute("submitForm",roleDTO);
		model.addAttribute("title","View");
		model.addAttribute("viewOnly",true);
 		return "role-action";
	}
	@GetMapping("/role/edit/{id}")
	public String roleEdit(Model model,@PathVariable("id") int id) {
		RoleDTO roleDTO =  roleService.findById(id);
		model.addAttribute("submitForm",roleDTO);
		model.addAttribute("title","Edit");
		model.addAttribute("viewOnly",false);
 		return "role-action";
	}
	@PostMapping("/role/delete")
	public String roleDelete(Model model,@RequestParam("id") int id,HttpSession session) {
		RoleDTO roleDTO =  roleService.findById(id);
		try {
			roleService.delete(roleDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
 		return "redirect:/role/list/1";
	}
	@PostMapping("/role/save")
	public String roleSave(Model model,@ModelAttribute("submitForm") @Validated RoleDTO roleDTO,BindingResult result, HttpSession session)  {
		
		if(result.hasErrors()) {
			if(roleDTO.getId() != 0) {
				model.addAttribute("title","Edit");
			}else {
				model.addAttribute("title","Add");
			}
			return "role-action";
		}
		if(roleDTO.getId() != 0) {
			try {
				roleService.update(roleDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật bị lỗi");
			}
		}else {
			try {
				roleService.save(roleDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm bị lỗi");
			}
		}
 		return "redirect:/role/list/1";
	}
}
