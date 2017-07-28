package com.servyou.course.service;

import java.util.List;

import com.servyou.course.meta.Content;
import com.servyou.course.meta.Person;
import com.servyou.course.meta.Product;

public interface ProductService {	
	public List<Product> getAllProducts();
	
	public Product getProductByPId(int id);
	//提交商品 新增
	public int submitProduct(String title,String image,String detail,double price,String summary);
	//提交商品 更新
	public int submitEditedProduct(int id,String title,String image,String detail,double price,String summary);
	
	public int deleteProduct(int id);
	
	public boolean existContent(int pId);
	
	public List<Product> getShowProductListByUser(Person peson);
	
	public Content getContentInfo(int contentId);
	//用户未购买的商品列表
	public List<Product> getUserUnPurchasedProductList(int uId);
	
	public int getLastInsertId();
}
