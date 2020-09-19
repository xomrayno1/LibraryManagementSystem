package com.tampro.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tampro.dto.AuthDTO;
import com.tampro.dto.AuthForm;
import com.tampro.dto.MenuDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.RoleDTO;
import com.tampro.service.AuthService;
import com.tampro.service.MenuService;
import com.tampro.service.RoleService;
import com.tampro.utils.Constant;

@Controller
public class MenuController {
	@Autowired
	MenuService menuService;
	@Autowired
	RoleService roleService;
	@Autowired
	AuthService authService;
	@GetMapping(value = {"/menu/list/","/menu/list"})
	public String redirect() {
		return "redirect:/menu/list/1";
	}
	
	@RequestMapping("/menu/list/{page}")
	public String showMenuList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") MenuDTO menuDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<MenuDTO> menuList = menuService.findAlls2(menuDTO, paging);
		List<RoleDTO> roles = roleService.findAll(null, null);
		for(MenuDTO item : menuList) {
			Map<Integer,Integer> mapAuth = new TreeMap(); // nếu dùng new HashMap thì khi nào sẽ là 2-0 1-0 3-0  
			List<AuthDTO> listAuth = authService.findByProperty("menu.id", item.getId());
			for(RoleDTO roleDTO : roles) {  // put cái base vào  mapAuth luôn luôn sẽ có số lượng role như đã xác định			
				mapAuth.put(roleDTO.getId(), 0);
			}
			for(AuthDTO authDTO : listAuth) { // put vào nó sẽ thay thế cái base và thay đổi Permission
				mapAuth.put(authDTO.getIdRole(),authDTO.getPermission()); // put vào cái key của base ở trên , permission được thay đổi
				System.out.println(mapAuth.keySet());
			}
			item.setMapAuth(mapAuth);
			//= > 1 cái menu  mình sẽ xác định xem từng Role có  cho nó check hay ko
		}
		model.addAttribute("listProduct", menuList);
		model.addAttribute("pageInfo", paging);
		model.addAttribute("roles", roles);
		if(session.getAttribute(Constant.MSG_SUCCESS)!=null) {
			model.addAttribute(Constant.MSG_SUCCESS, session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if(session.getAttribute(Constant.MSG_ERROR)!=null) {
			model.addAttribute(Constant.MSG_ERROR, session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
 		return "menu-list";
	}

	@RequestMapping("/menu/permission")
	public String permission(Model model ) {
		model.addAttribute("submitForm", new AuthForm());
		innitSelect(model);
		return "menu-permission";
	}
	@RequestMapping("/menu/update-permission")
	public String updatePermission(Model model ,@ModelAttribute("searchForm") AuthForm authForm,HttpSession session) {
		try {
			authService.update(authForm);
			session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Cập thật thất bại");
		}
		return "redirect:/menu/list/1";
	}
	@PostMapping("/menu/change-status")
	public String permission(Model model,@RequestParam("id")int id,HttpSession session) {	
		try {		
			menuService.changeStatus(id);
			session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Cập nhật thất bại");
		}
		return "redirect:/menu/list/1";
	}
	
	public void innitSelect(Model model) {
		List<MenuDTO> listMenu = menuService.findAll(null, null);
		List<RoleDTO> listRole = roleService.findAll(null, null);
		Map<Integer, String> mapRole = new HashedMap<Integer, String>();
		for(RoleDTO role : listRole) {
			mapRole.put(role.getId(), role.getName());
		}
		Map<Integer,String> mapMenu = new TreeMap<>();
		Collections.sort(listMenu,(o1,o2)->o1.getUrl().compareTo(o2.getUrl()));
		for(MenuDTO menuDTO : listMenu) {
			mapMenu.put(menuDTO.getId(), menuDTO.getUrl());
		}
		model.addAttribute("mapRole", mapRole);
		model.addAttribute("mapMenu", mapMenu);
	}
}
