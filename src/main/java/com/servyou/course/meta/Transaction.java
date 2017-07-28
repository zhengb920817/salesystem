package com.servyou.course.meta;

public class Transaction {
	//
	private int id;
	// 内容id
	private int contentId;
	// 用户id
	private int personId;
	// 购买价格
	private double price;
	// 购买时间
	private Long time;

	private int buyNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public String toString() {
		return String.format("交易信息：商品id：%s，用户id：%s，价格：%s，数量：%s，购买时间：%s", 
				contentId, personId, price, buyNum, time);
	}
}
