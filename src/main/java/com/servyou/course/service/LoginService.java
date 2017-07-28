package com.servyou.course.service;

import com.servyou.course.meta.Person;

public interface LoginService {
	public Person getPerson(String userName,String password);
	public int getUserType(String userName, String password);
	public boolean validUser(String userName,String password);
}
