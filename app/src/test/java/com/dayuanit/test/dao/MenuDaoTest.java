package com.dayuanit.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.dao.MenuDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-config-test.xml")
@Transactional("txManager")
public class MenuDaoTest {
	
	@Autowired
	private MenuDao menuDao;
	
	@Test
	public void testListMenus() {
		List<Map<String, Object>> list = menuDao.listMenus();
		assertEquals(3, list.size());
	}

}
