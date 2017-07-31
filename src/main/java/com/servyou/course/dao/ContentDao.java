package com.servyou.course.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.servyou.course.meta.Content;

public interface ContentDao {
	@Insert("insert into content(price, title, icon, abstract, text) values(#{price},#{title},#{icon},#{contentAbstract},"
			+ "#{text})")
	public void addPoduct(Content content);

	@Update("update content set price = #{price}, title = #{title},icon = #{icon},abstract = #{contentAbstract}, text=#{text} "
			+ " where id = #{id}")
	public void updateProduct(Content newContent);
	
	@Select("select * from content where id = #{cId}")
	@Results({
			@Result(id = true, column = "id",property = "id"),
			@Result(property = "price", column = "price"), 
			@Result(property = "title", column = "title"),
			@Result(property = "icon", column = "icon"), 
			@Result(property = "contentAbstract", column = "abstract"),
			@Result(property = "text", column = "text") })
	public Content getContentById(@Param("cId") int id);

	@Delete("DELETE from content WHERE id = #{contId} ")
	public void deleteContentById(@Param("contId") int id);
	
	@Select("select count(*) from content where id = #{contId}")
	public boolean existContent(@Param("contId") int contentId);
}
