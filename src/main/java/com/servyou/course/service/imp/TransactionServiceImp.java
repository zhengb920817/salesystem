package com.servyou.course.service.imp;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servyou.course.dao.TransactionDao;
import com.servyou.course.meta.Product;
import com.servyou.course.meta.Transaction;
import com.servyou.course.service.TransactionService;

@Service("transactionService")
public class TransactionServiceImp implements TransactionService {
	private TransactionDao transactionDao;
	
	@Autowired
	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}
	
	public List<Product> getBuyList(int userId) {
		// TODO Auto-generated method stub
		List<Product> buyList = new LinkedList<Product>();
		buyList = transactionDao.getBuyListByUserId(userId);
		for(Product pro:buyList) {
			pro.setIsBuy(true);
		}
		return buyList;
	}

	@Override
	public int getProductBuyNum(int userId, int contentId) {
		// TODO Auto-generated method stub
		return transactionDao.getBuyNum(userId, contentId);
	}

	@Override
	@Transactional
	public void doBuyProduct(Transaction transaction) {
		// TODO Auto-generated method stub
		transactionDao.addTransaction(transaction);
	}

	@Override
	public void addTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		transactionDao.addTransaction(transaction);
	}

	@Override
	public List<Product> getSelledList() {
		// TODO Auto-generated method stub
		List<Product> selledList = new LinkedList<Product>();
		selledList = transactionDao.getSelledList();
		for(Product pro:selledList) {
			pro.setIsSell(true);
		}
		return selledList;
	}

}
