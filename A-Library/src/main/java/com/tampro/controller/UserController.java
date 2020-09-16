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
import com.tampro.dto.UsersDTO;
import com.tampro.service.RoleService;
import com.tampro.service.UserService;
import com.tampro.utils.Constant;
import com.tampro.validator.UserValidator;

@Controller
public class UserController {
	@InitBinder
	public void webInitBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return ;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:yy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == UsersDTO.class) {
			binder.setValidator(userValidator);
		}
	}

	@Autowired
	UserValidator userValidator;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	@GetMapping(value = {"/user/list/","/user/list"})
	public String redirect() {
		return "redirect:/user/list/1";
	}
	
	@RequestMapping("/user/list/{page}")
	public String showUserList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") UsersDTO userDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<UsersDTO> dtos  = userService.findAll(userDTO, paging);
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
 		return "user-list";
	}
	@GetMapping("/user/add")
	public String userAdd(Model model) {
		List<RoleDTO> list = roleService.findAll(null, null);
		model.addAttribute("roles", list);
		model.addAttribute("submitForm",new UsersDTO());
		model.addAttribute("title","Add");
		model.addAttribute("viewOnly",false);
 		return "user-action";
	}
	@GetMapping("/user/view/{id}")
	public String userView(Model model,@PathVariable("id") int id) {
		UsersDTO userDTO =  userService.findById(id);
		RoleDTO roleDTO = userDTO.getUserRolesDTOs().iterator().next().getRoleDTO();
		model.addAttribute("role", roleDTO);
		model.addAttribute("submitForm",userDTO);
		model.addAttribute("title","View");
		model.addAttribute("viewOnly",true);
 		return "user-action";
	}
	@GetMapping("/user/edit/{id}")
	public String userEdit(Model model,@PathVariable("id") int id) {
		UsersDTO userDTO =  userService.findById(id);
		List<RoleDTO> list = roleService.findAll(null, null);
		model.addAttribute("roles", list);
		model.addAttribute("submitForm",userDTO);
		model.addAttribute("title","Edit");
		model.addAttribute("viewOnly",false);
 		return "user-action";
	}
	@PostMapping("/user/delete")
	public String userDelete(Model model,@RequestParam("id") int id,HttpSession session) {
		UsersDTO userDTO =  userService.findById(id);
		try {
			userService.delete(userDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
 		return "redirect:/user/list/1";
	}
	@PostMapping("/user/save")
	public String userSave(Model model,@ModelAttribute("submitForm") @Validated UsersDTO userDTO,BindingResult result, HttpSession session)  {
		
		if(result.hasErrors()) {
			if(userDTO.getId() != 0) {
				model.addAttribute("title","Edit");
			}else {
				model.addAttribute("title","Add");
			}
			return "user-action";
		}
		if(userDTO.getId() != 0) {
			try {
				userService.update(userDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật bị lỗi");
			}
		}else {
			try {
				userService.save(userDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm bị lỗi");
			}
		}
 		return "redirect:/user/list/1";
	}

}
