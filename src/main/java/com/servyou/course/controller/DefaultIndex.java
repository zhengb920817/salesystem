package com.servyou.course.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.servyou.course.meta.Person;
import com.servyou.course.meta.Product;
import com.servyou.course.service.ProductService;

@Controller
public class DefaultIndex {
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@ModelAttribute("title")
	public String getIndexTitle() {
		return "首页";
	}

	// 首页
	@RequestMapping(path = { "/", "index" })
	public ModelAndView defaultIndex(@RequestParam(name = "type", required = false) Integer type, HttpSession session) {
		ModelAndView indexView = new ModelAndView("index");
		List<Product> showProductList = null;
		Integer showType;
		if (type == null) {
			showType = 0;
		} else {
			showType = 1;
		}

		session.setAttribute("listType", showType);
		// 用户已登录的情况下
		if (session.getAttribute("user") != null) {
			System.out.println("用户已登录...");
			Person person = (Person) session.getAttribute("user");
			int userType = person.getUserType();
			System.out.println("用户类型：" + userType);
			if ((userType == 0) && (showType == 1)) {
				showProductList = productService.getUserUnPurchasedProductList(person.getId());
			} else {
				showProductList = productService.getShowProductListByUser(person);
			}
		} else {
			// 未登录情况下展示所有内容
			showProductList = productService.getAllProducts();
			for (Product pro : showProductList) {
				System.out.println(pro);
			}
		}

		System.out.println(showProductList.size());
		for (Product pro : showProductList) {
			System.out.println(pro);
		}
		indexView.addObject("productList", showProductList);
		return indexView;
	}

}
