package com.servyou.course.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserLoginAndLogOut {
	
	@ModelAttribute("title")
	public String getTitle() {
		return "用户登录";
	}
	
	// 用户登录
	@RequestMapping("/login")
	public String userLoginPage(ModelMap map) {
		return "login";
	}

	@RequestMapping("/logout")
	public String logOut(ModelMap map, HttpSession httpSession) {
		httpSession.removeAttribute("user");
		return "login";
	}
}
