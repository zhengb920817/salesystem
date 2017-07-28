package com.servyou.course.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.servyou.course.meta.Person;
import com.servyou.course.meta.Product;
import com.servyou.course.service.TransactionService;

@Controller
public class Account {
	private TransactionService transactionService;

	@Autowired
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@RequestMapping("/account")
	public ModelAndView getAccoutList(HttpSession httpSession) {
		List<Product> buyList = new ArrayList<Product>();
		if (httpSession.getAttribute("user") != null) {
			Person user = (Person) httpSession.getAttribute("user");
			int userId = user.getId();
			buyList = transactionService.getBuyList(userId);

		} else {
			buyList = transactionService.getBuyList(0);
		}

		ModelAndView view = new ModelAndView("account");
		view.addObject("buyList", buyList);
		view.addObject("title", "账户管理");
		return view;

	}
	
	@RequestMapping("/settleAccount")
	public ModelAndView settleAccount() {
		ModelAndView setView = new ModelAndView("settleAccount");
		setView.addObject("title", "购物车");
		return setView;
	}

}
