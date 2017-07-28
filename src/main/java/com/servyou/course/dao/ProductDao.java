package com.servyou.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.servyou.course.meta.Product;

public interface ProductDao {	
	@Select(" select cont.id as id,cont.price as price,cont.title as title, cont.icon as image,cont.abstract as summary,"
			+ " cont.text as detail, trx.personId as personId, trx.price as buyPrice, trx.price as sellPrice, "
			+ " trx.time as buyTime,trx.buyNum as buyNum from content cont left join trx on cont.id = trx.contentId where cont.id = #{cId} ")
	public Product getProductById(@Param("cId") int id);
	
	@Select("select id as id,price as price ,title as title,icon as image, abstract as summary,text as detail from content")
	public List<Product> getAllContens();
	
	@Select("select cont.id as id,cont.title as title,cont.icon as image,cont.abstract as summary," + 
			"cont.text as detail, cont.price as price,trx.time as buyTime,trx.buyNum as buyNum from content cont left join trx on cont.id = trx.contentId")
	public List<Product> getShowProduct();
	
	@Select("select LAST_INSERT_ID() as lastid")
	@Result(javaType = Integer.class)
	public int getLastInsertId();
 }
