package com.servyou.course.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.servyou.course.meta.Person;
import com.servyou.course.meta.Product;
import com.servyou.course.service.ProductService;

@Controller
public class Edit {

	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/edit", method = { RequestMethod.GET })
	public ModelAndView editProduct(@RequestParam("id") int pId, HttpSession httpSession) {
		ModelAndView view = new ModelAndView("edit");
		view.addObject("title", "编辑商品");
		Product editProduct = productService.getProductByPId(pId);
		if (httpSession.getAttribute("user") != null) {
			Person user = (Person) httpSession.getAttribute("user");
			//如果是卖家  则加入当前正编辑的商品至session中
			if (user.getUserType() == 1) {
				httpSession.setAttribute("editingProduct", editProduct);
			}
		}
		view.addObject("product", editProduct);
		return view;
	}
}
