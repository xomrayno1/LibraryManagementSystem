package com.tampro.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tampro.dto.AuthDTO;
import com.tampro.dto.AuthForm;
import com.tampro.dto.MenuDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.RoleDTO;
import com.tampro.entity.Role;
import com.tampro.service.AuthService;
import com.tampro.service.MenuService;
import com.tampro.service.RoleService;

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
	public String showMenuList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") MenuDTO menuDTO) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<MenuDTO> menuList = menuService.findAll(menuDTO, paging);
		List<RoleDTO> roles = roleService.findAll(null, null);
		Collections.sort(roles,(o1,o2)->o1.getId() - o2.getId());
		for(MenuDTO item : menuList) {
			Map<Integer, Integer> mapAuth = new HashedMap<Integer, Integer>();
			List<AuthDTO> listAuth = authService.findByProperty("menu.id", item.getId());
			for(RoleDTO roleDTO : roles) {  // put cái base vào  mapAuth luôn luôn sẽ có số lượng role như đã xác định
				mapAuth.put(roleDTO.getId(), 0);
			}
			for(AuthDTO authDTO : listAuth) { // put vào nó sẽ thay thế cái base và thay đổi Permission
				mapAuth.put(authDTO.getIdRole(), authDTO.getPermission()); // put vào cái key của base ở trên , permission được thay đổi
			}
			item.setMapAuth(mapAuth);
			//= > 1 cái menu  mình sẽ xác định xem từng Role có  cho nó check hay ko
		}
		
		model.addAttribute("listProduct", menuList);
		model.addAttribute("pageInfo", paging);
		model.addAttribute("roles", roles);
 		return "menu-list";
	}
	@RequestMapping("/menu/permission")
	public String permission(Model model ) {
		model.addAttribute("submitForm", new AuthForm());
		innitSelect(model);
		return "menu-permission";
	}
	
	public void innitSelect(Model model) {
		List<MenuDTO> listMenu = menuService.findAll(null, null);
		List<RoleDTO> listRole = roleService.findAll(null, null);
		Map<Integer, String> mapRole = new HashedMap<Integer, String>();
		for(RoleDTO role : listRole) {
			mapRole.put(role.getId(), role.getName());
		}
		Map<Integer,String> mapMenu = new HashedMap<Integer, String>();
		for(MenuDTO menuDTO : listMenu) {
			mapMenu.put(menuDTO.getId(), menuDTO.getUrl());
		}
		model.addAttribute("mapRole", mapRole);
		model.addAttribute("mapMenu", mapMenu);
	}
}
