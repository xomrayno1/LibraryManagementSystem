package com.tampro.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tampro.dto.AuthDTO;
import com.tampro.dto.MenuDTO;
import com.tampro.dto.RoleDTO;
import com.tampro.dto.UserRolesDTO;
import com.tampro.dto.UsersDTO;
import com.tampro.service.UserService;
import com.tampro.utils.Constant;

public class FilterSystem  implements HandlerInterceptor{
	@Autowired
	UserService userService;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Uri :"+request.getRequestURI());
		
		UsersDTO usersDTO = (UsersDTO) request.getSession().getAttribute(Constant.USER_INFO);
		if(usersDTO == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}else {
			String url =  request.getServletPath();
			System.out.println(url);
			if(!hasPermission(url,usersDTO)) {
				response.sendRedirect(request.getContextPath() + "/access-denied");
				return false;
			}
		}
		return true;
	}
	public boolean hasPermission(String url, UsersDTO usersDTO) {
		if(url.contains("/index") || url.contains("/access-denied")|| url.contains("/login") || url.contains("/logout")) {
			return true;
		}
		UserRolesDTO userRolesDTO = usersDTO.getUserRolesDTOs().iterator().next();
		RoleDTO roleDTO = userRolesDTO.getRoleDTO();
		for(AuthDTO dto : roleDTO.getAuths()) {
			MenuDTO menuDTO = dto.getMenuDTO();			
			if(url.contains(menuDTO.getUrl())) {
				return  dto.getPermission() == 1;
			}
		}
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
