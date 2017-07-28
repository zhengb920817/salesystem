package com.servyou.course.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servyou.course.dao.PersonDao;
import com.servyou.course.meta.Person;
import com.servyou.course.service.LoginService;

@Service("loginService")
public class LoginServiceImp implements LoginService {
	private PersonDao personDao;

	@Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public Person getPerson(String userName, String password) {
		return this.personDao.getPerson(userName, password);
	}

	public int getUserType(String userName, String password) {
		return this.personDao.getUserType(userName, password);
	}

	@Override
	public boolean validUser(String userName, String password) {
		Person person = personDao.getPerson(userName, password);
		return (person != null);
	}

}
