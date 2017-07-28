package com.servyou.course.service;

import java.util.List;

import com.servyou.course.meta.Product;
import com.servyou.course.meta.Transaction;

public interface TransactionService {	
	public void doBuyProduct(Transaction transaction);
	public List<Product> getBuyList(int userId);
	public int getProductBuyNum(int userId, int contentId);
	public void addTransaction(Transaction transaction);
	public List<Product> getSelledList();
}
