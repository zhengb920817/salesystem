package com.servyou.course.meta;

public class Product {
	// 产品id
	private int id;
	// 产品标题
	private String title;
	// 图片地址
	private String image;
	// 价格
	private double price;
	// 当前用户是否已经购买
	private boolean isBuy;
	// 是否已经售出
	private boolean isSell;
	// 全文
	private String detail;
	// 摘要
	private String summary;
	// 购买时价格
	private double buyPrice;
	// 购买时间
	private long buyTime;
	// 购买数量
	private int buyNum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean getIsSell() {
		return isSell;
	}

	public void setIsSell(boolean isSell) {
		this.isSell = isSell;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public long getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(long buyTime) {
		this.buyTime = buyTime;
	}

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public String toString() {
		return String.format("产品id:%s，产品标题：%s，图片：%s，价格：%s，详细信息：%s，摘要：%s，购买价格：%s，购买时间:%s", id, title, image, price,
				detail, summary, buyPrice, buyTime);
	}

	public boolean getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}
}
