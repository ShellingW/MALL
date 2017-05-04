package com.dayuanit.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.dao.UserDao;
import com.dayuanit.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-config-test.xml")
@Transactional("txManager")
public class UserDaoTest {
	
	@Autowired
	private UserDao userDao;
	
	private User user;
	
	@Before
	public void init() {
		user = new User();
		user.setBirthday(new Date());
		user.setEmail("22222@163.com");
		user.setPassword("111");
		user.setPhone("1360000000");
		user.setSex("F");
		user.setStatus(0);
		user.setUsername("jack");
	}
	
	@Test
	@Rollback
	public void testAddUser() {
		int rows = userDao.addUser(user);
		assertEquals(1, rows);
	}
	
	@Test
	@Rollback
	public void testGetUserByUsername() {
		userDao.addUser(user);
		User tmpUser = userDao.getUserByUsername(user.getUsername());
		assertEquals(user.getUsername(), tmpUser.getUsername());
	}

}
