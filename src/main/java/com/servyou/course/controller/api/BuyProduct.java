package com.servyou.course.controller.api;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.servyou.course.meta.Content;
import com.servyou.course.meta.Person;
import com.servyou.course.meta.Transaction;
import com.servyou.course.service.ProductService;
import com.servyou.course.service.TransactionService;
import com.servyou.course.utils.ResponseCode;

@Controller
@RequestMapping("/api")
public class BuyProduct {
	private TransactionService transactionService;
	
	private ProductService productService;

	@Autowired
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	private Long getBuyTime() {
		GregorianCalendar calendar = new GregorianCalendar(Locale.CHINA);
		return calendar.getTime().getTime();
	}

	private List<Transaction> getBuyList(final int userId, String reuqestBody) {
		List<Transaction> buyList = new LinkedList<Transaction>();
		List<JSONObject> jsonObjectList = JSON.parseArray(reuqestBody, JSONObject.class);
        for( JSONObject jsonObject : jsonObjectList ){
            Integer id = jsonObject.getInteger("id");
            Integer buyNum = jsonObject.getInteger("number");
            Transaction transaction = new Transaction();
            transaction.setBuyNum(buyNum);
            transaction.setContentId(id);
            transaction.setPersonId(userId);;
            Content content = productService.getContentInfo(id);
            transaction.setPrice(content.getPrice());
            transaction.setTime(getBuyTime());
            buyList.add(transaction);
        }
		return buyList;
	}

	@RequestMapping(value = "/buy", method = { RequestMethod.POST })
	@ResponseBody
	public ModelMap doBuyProduct(@RequestBody String requestBody, HttpSession httpSession, ModelMap map) {
		System.out.println("购买商品请求体：" + requestBody);
		if (httpSession.getAttribute("user") != null) {
			Person user = (Person) httpSession.getAttribute("user");
			List<Transaction> buyList = getBuyList(user.getId(), requestBody);
			for (Transaction transaction : buyList) {
				System.out.println(transaction);
				transactionService.addTransaction(transaction);
			}
			map.addAttribute("code", ResponseCode.REQUST_SUCCESS);
			map.addAttribute("result", true);
			map.addAttribute("message", "购买成功");
		} else {
			map.addAttribute("code", ResponseCode.REQUEST_FAIL);
			map.addAttribute("result", false);
			map.addAttribute("message", "购买失败，请先登录！");
		}

		return map;

	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}
