package com.servyou.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.servyou.course.meta.Product;
import com.servyou.course.service.ProductService;

@Controller
public class PublicSubmit {
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/publicSubmit", method = { RequestMethod.POST })
	public ModelAndView publicSubmit(Product submitProduct) {
		productService.submitProduct(submitProduct.getTitle(), submitProduct.getImage(), submitProduct.getDetail(),
				submitProduct.getPrice(), submitProduct.getSummary());
		int subPid = productService.getLastInsertId();
		System.out.println("最新一次插入id ： " + subPid);
		Product product = productService.getProductByPId(subPid);
		ModelAndView view = new ModelAndView("publicSubmit");
		view.addObject("product", product);
		view.addObject("title", "发布商品");
		return view;
	}
}
