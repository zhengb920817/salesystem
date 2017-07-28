package com.servyou.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.servyou.course.meta.Product;
import com.servyou.course.meta.Transaction;

public interface TransactionDao {
	//添加交易
	@Insert("insert into trx(contentId,personId,price,time,buyNum) values(#{contentId},#{personId},"
			+ "#{price},#{time}, #{buyNum})")
	public void addTransaction(Transaction transaction);
	//更新交易
	@Update("update trx set contentId = #{contentId}, personId = #{personId}, price = #{price}, time = #{time},"
			+ " buyNum = #{buyNum}"
			+ " where id = #{txId}")
	public void updateTransaction(@Param("txId") int txId, Transaction transaction);

	@Delete("delete from trx where id = #{transactionId}")
	public void deleteTransaction(@Param("transactionId") int transactionId);
	
	//获取用户已购买产品信息
	@Select("select trx.contentId as id, trx.price as buyPrice,trx.price as sellPrice, trx.time as buyTime, trx.buyNum as buyNum,"
			+ " cont.title as title,cont.icon as image,cont.abstract as summary, cont.text as detail "
			+ " from trx left join content cont on trx.contentId = cont.id where trx.personId = #{userId}")
	public List<Product> getBuyListByUserId(@Param("userId") int userId);
	
	//统计商品购买数量
	@Select("select buyNum from trx where personId = #{pId} and contentId = #{cId} ")
	@Result(javaType = Integer.class)
	public int getBuyNum(@Param("pId") int userId, @Param("cId") int contentId);
	
	@Select("select trx.contentId as id, trx.price as buyPrice, trx.time as buyTime ,"
			+ " cont.title as title,cont.icon as image,cont.abstract as summary, cont.text as detail "
			+ " from trx left join content cont on trx.contentId = cont.id order by id asc")
	public List<Product> getSelledList();
}
