package com.servyou.course.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.servyou.course.meta.Person;
import com.servyou.course.service.LoginService;
import com.servyou.course.utils.ResponseCode;

@Controller
@RequestMapping(value = "/api")
public class LoginAPI {
	private LoginService loginService;

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	@ResponseBody
	public ModelMap doUserLogin(@RequestParam("userName") String userName, 
			@RequestParam("password") String password,
			HttpSession httpSession, ModelMap map) {
		System.out.println("登录用户名：" + userName);
		System.out.println("登录用户密码：" + password);
		boolean validUser = loginService.validUser(userName, password);
		if (validUser){
			System.out.println("用户名密码有效");
			map.addAttribute("code", ResponseCode.REQUST_SUCCESS);
			map.addAttribute("message", "success");
			map.addAttribute("result", true);
			Person user = loginService.getPerson(userName, password);
			httpSession.setAttribute("user", user);
		}
		else
		{
			map.addAttribute("code", ResponseCode.REQUEST_FAIL);
			map.addAttribute("message", "failed");
			map.addAttribute("result", false);
			httpSession.removeAttribute("user");
		}
		return map;
	}
}
