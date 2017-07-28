package com.servyou.course.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.servyou.course.meta.Product;
import com.servyou.course.service.ProductService;
import com.servyou.course.utils.ResponseCode;

@Controller
@RequestMapping("/api")
public class DeleteProduct {
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	@ResponseBody
	public ModelMap deleteProductById(@RequestParam("id") int pId) {
		Product product = productService.getProductByPId(pId);
		Map<String, Object> retAttr = new HashMap<String, Object>();
		if (product != null) {
			int result = productService.deleteProduct(pId);
			if (result == 0) {
				retAttr.put("code", ResponseCode.REQUST_SUCCESS);
				retAttr.put("message", "删除成功");
				retAttr.put("result", true);
			} else {
				retAttr.put("code", ResponseCode.REQUEST_FAIL);
				retAttr.put("message", "删除失败");
				retAttr.put("result", false);
			}
		} else {
			retAttr.put("code", ResponseCode.REQUEST_FAIL);
			retAttr.put("message", "删除失败");
			retAttr.put("result", false);
		}
		ModelMap model = new ModelMap();
		model.addAllAttributes(retAttr);
		return model;
	}
}
