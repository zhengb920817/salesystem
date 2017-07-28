package com.servyou.course.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import com.servyou.course.meta.Person;

public interface PersonDao {
	@Select("select * from person where userName = #{userName} and password = #{password}")
	public Person getPerson(@Param("userName") String userName, @Param("password") String password);

	@Insert("insert into person(userName,password,nickName,userType) values "
			+ "(#{userName}, #{passWord}, #{nickName}, #{userType})")
	public void insertUser(Person newPerson);
	
	@Select("select userType from person where username = #{uName} and password = #{uPassword}")
	@Result(javaType = Integer.class)
	public int getUserType(@Param("uName") String userName, @Param("uPassword") String password);
}
