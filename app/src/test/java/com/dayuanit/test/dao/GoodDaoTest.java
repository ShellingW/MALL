package com.dayuanit.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.dao.GoodDao;
import com.dayuanit.domain.Good;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-config-test.xml")
@Transactional("txManager")
public class GoodDaoTest {
	
	@Autowired
	private GoodDao goodDao;
	
	@Test
	public void testListGoodsByMenuId() {
		List<Good> goods = goodDao.listGoodsByMenuId(1);
		assertEquals(8, goods.size());
	}
	
	@Test
	public void testGetGood() {
		Good good = goodDao.getGood(2);
		assertEquals(2, good.getId().intValue());
	}

}
