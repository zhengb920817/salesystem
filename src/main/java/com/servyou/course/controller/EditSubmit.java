package com.servyou.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.servyou.course.meta.Product;
import com.servyou.course.service.ProductService;

@Controller
public class EditSubmit {
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/editSubmit", method = { RequestMethod.POST })
	public ModelAndView submitEditProduct(@RequestParam("id") int pId, Product submitProduct) {
		System.out.println("提交编辑产品id：" + pId);
		System.out.println("提交编辑产品信息：" + submitProduct) ;
		ModelAndView view = new ModelAndView("editSubmit");
		view.addObject("title", "编辑商品");
		productService.submitEditedProduct(pId, submitProduct.getTitle(), submitProduct.getImage(),
				submitProduct.getDetail(), submitProduct.getPrice(), submitProduct.getSummary());
//		int lastInsertId = productService.getLastInsertId();
//		System.out.println("最近一次插入id：" + lastInsertId);
		Product product = productService.getProductByPId(pId);
		view.addObject("product", product);
		return view;
	}
}
