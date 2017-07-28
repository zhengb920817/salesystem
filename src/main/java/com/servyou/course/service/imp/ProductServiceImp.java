package com.servyou.course.service.imp;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servyou.course.dao.ContentDao;
import com.servyou.course.dao.ProductDao;
import com.servyou.course.meta.Content;
import com.servyou.course.meta.Person;
import com.servyou.course.meta.Product;
import com.servyou.course.service.ProductService;

@Service("productService")
public class ProductServiceImp implements ProductService {
	private ProductDao productDao;

	private ContentDao contentDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Autowired
	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}

	public Content getContentInfo(int contentId) {
		return this.contentDao.getContentById(contentId);
	}

	// 获取商品信息(包含交易信息)
	public Product getProductByPId(int id) {
		Product product = productDao.getProductById(id);
		if (product.getBuyNum() > 0) {
			product.setIsBuy(true);
			product.setIsSell(true);
		} else {
			product.setIsBuy(false);
			product.setIsSell(false);
		}
		return product;
	}

	// 发布产品
	@Transactional
	public int submitProduct(String title, String image, String detail, double price, String summary) {
		Content submitContent = new Content();
		submitContent.setTitle(title);
		submitContent.setIcon(image);
		submitContent.setContentAbstract(summary);
		submitContent.setText(detail);
		submitContent.setPrice(price);
		this.contentDao.addPoduct(submitContent);
		return 0;
	}

	// 编辑提交
	@Transactional
	public int submitEditedProduct(int id, String title, String image, String detail, double price, String summary) {
		// TODO Auto-generated method stub
		Content newContent = new Content();
		newContent.setId(id);
		newContent.setTitle(title);
		newContent.setIcon(image);
		newContent.setText(detail);
		newContent.setPrice(price);
		newContent.setContentAbstract(summary);
		this.contentDao.updateProduct(newContent);
		return 0;
	}

	// 删除商品
	@Transactional
	public int deleteProduct(int id) {
		// TODO Auto-generated method stub
		this.contentDao.deleteContentById(id);
		return 0;
	}

	// 获取所有商品
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productDao.getAllContens();
	}

	@Override
	// 首页展示的商品列表 根据用户
	public List<Product> getShowProductListByUser(Person person) {
		List<Product> products = productDao.getShowProduct();

		if (person.getUserType() == 0) {
			for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				if (product.getBuyNum() > 0) {
					product.setIsBuy(true);
				} else {
					product.setIsBuy(false);
				}
			}
		} else {
			for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				if (product.getBuyNum() > 0) {
					product.setIsSell(true);
				} else {
					product.setIsSell(false);
				}
			}
		}
		return products;
	}

	@Override
	// content库中是否存在商品
	public boolean existContent(int pId) {
		return contentDao.existContent(pId);
	}

	@Override
	public List<Product> getUserUnPurchasedProductList(int uId) {
		List<Product> productList = productDao.getShowProduct();
		for (Iterator<Product> iterator = productList.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			if (product.getBuyNum() > 0) {
				iterator.remove();
			}
		}
		return productList;
	}

	@Override
	public int getLastInsertId() {
		// TODO Auto-generated method stub
		return productDao.getLastInsertId();
	}
}
