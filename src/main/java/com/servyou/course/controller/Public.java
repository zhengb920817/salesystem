package com.servyou.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Public {

	@RequestMapping("/public")
	public ModelAndView publicPage() {
		ModelAndView view = new ModelAndView("public");
		view.addObject("title", "商品发布");
		return view;
	}
}
