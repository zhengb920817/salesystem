package com.servyou.course.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.servyou.course.meta.Product;
import com.servyou.course.service.ProductService;

@RestController
public class Show {
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping("/show")
	public ModelAndView getProductShow(@RequestParam("id") int pId, HttpSession httpSession) {
		Product product = null;
		System.out.println("查看商品id：" + pId);
		// 已登录
		if (httpSession.getAttribute("user") != null) {
			product = productService.getProductByPId(pId);
			// Person curUser = (Person) httpSession.getAttribute("user");
		} else {
			product = productService.getProductByPId(pId);
		}
		System.out.println("查看商品信息" + product);
		ModelAndView retView = new ModelAndView();
		retView.setViewName("show");
		retView.addObject("product", product);
		retView.addObject("title", "商品详情");
		return retView;

	}

}
