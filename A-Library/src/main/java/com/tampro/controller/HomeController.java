package com.tampro.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tampro.dto.AuthDTO;
import com.tampro.dto.MenuDTO;
import com.tampro.dto.RoleDTO;
import com.tampro.dto.UserRolesDTO;
import com.tampro.dto.UsersDTO;
import com.tampro.service.MenuService;
import com.tampro.service.UserService;
import com.tampro.utils.Constant;
import com.tampro.validator.LoginValidator;

@Controller
public class HomeController {
	@Autowired
	LoginValidator loginValidator;
	@Autowired
	UserService  userService;
	@Autowired
	MenuService menuService;
	
	@InitBinder
	public void initParam(WebDataBinder dataBinder) {
		if(dataBinder.getTarget() == null) {
			return;
		}
		if(dataBinder.getTarget().getClass() == UsersDTO.class) {
			dataBinder.setValidator(loginValidator);
		}
	}
	@GetMapping(value = {"/index"})
	public String home(Model model ) {
	
		return "index";
	}
	@GetMapping(value = {"/access-denied"})
	public String accessDenied(Model model ) {
	
		return "access-denied";
	}
	@GetMapping(value = {"/login"} )
	public String login(Model model) {
		model.addAttribute("loginForm",new UsersDTO());
		return "login";
	}
	
	@PostMapping("/processLogin")
	public String processLogin(Model model,@ModelAttribute("loginForm") @Validated UsersDTO usersDTO,BindingResult result ,HttpSession session) {
		if(result.hasErrors()) {
			return "login";
		}
		UsersDTO user = userService.findByProperty("username", usersDTO.getUsername()).get(0);
		if(user != null) {
			session.setAttribute(Constant.USER_INFO, user);
		}
		UserRolesDTO userRolesDTOs  = user.getUserRolesDTOs().iterator().next();
		RoleDTO roleDTO  = userRolesDTOs.getRoleDTO();
		Set<AuthDTO> authDTOs = roleDTO.getAuths();
		List<MenuDTO> listMenuParentDTOs = new ArrayList<MenuDTO>();
		List<MenuDTO> listChildDTOs = new ArrayList<MenuDTO>();
		for(AuthDTO authDTO : authDTOs) {
			MenuDTO menuDTO = authDTO.getMenuDTO();
			if(authDTO.getActiveFlag() == 1 && authDTO.getPermission() == 1) {
				if( authDTO.getMenuDTO().getOrderIndex() > -1 && authDTO.getMenuDTO().getParentId() == 0) {
					menuDTO.setIdMenu(menuDTO.getUrl().replace("/", "")+"Id");
					listMenuParentDTOs.add(menuDTO);
				}else if(authDTO.getMenuDTO().getOrderIndex() > -1 && authDTO.getMenuDTO().getParentId() > 0) {
					listChildDTOs.add(menuDTO);
					menuDTO.setIdMenu(menuDTO.getUrl().replace("/", "")+"Id");
				}
			}
		}
		for(MenuDTO menuParent : listMenuParentDTOs) {
			List<MenuDTO> list = new ArrayList<MenuDTO>();
			for(MenuDTO  menuChild : listChildDTOs) {
				if(menuParent.getId() == menuChild.getParentId()) {
					list.add(menuChild);
				}
			}
			menuParent.setChild(list);
		}
		sortMenu(listMenuParentDTOs);
		for(MenuDTO menuParent : listMenuParentDTOs) {
			sortMenu(menuParent.getChild());
		}
		
		session.setAttribute(Constant.MENU_SESSION_STRING, listMenuParentDTOs);
		return "redirect:/index";
	}
	@GetMapping(value = {"/logout"} )
	public String logout(Model model,HttpSession session) {
		session.removeAttribute(Constant.USER_INFO);
		return "redirect:/login";
	}
	
	
	public static void sortMenu(List<MenuDTO> menuDTOs) {
		Collections.sort(menuDTOs, new Comparator<MenuDTO>() {

			public int compare(MenuDTO o1, MenuDTO o2) {
				// TODO Auto-generated method stub
				return o1.getOrderIndex() - o2.getOrderIndex();
			}
			
		});
	}
	
}
