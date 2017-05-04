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

import com.dayuanit.dao.OrderDao;
import com.dayuanit.domain.Order;
import com.dayuanit.mallenum.PayTypeEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-config-test.xml")
@Transactional("txManager")
public class OrderDaoTest {
	
	@Autowired
	private OrderDao orderDao;
	
	private Order order;
	
	@Before
	public void init() {
		order = new Order();
		order.setAmount("10");
		order.setDeadTime(new Date());
		order.setGoodId(2);
		order.setNum(10);
		order.setStatus(1);
		order.setUnitPrice("2");
		order.setUserId(1000);
		order.setAddress("安徽省合肥市哒六和");
		order.setPayType(PayTypeEnum.ALIPAY.getType());
	}
	
	@Test
	@Rollback
	public void testAddOrder() {
		int rows = orderDao.addOrder(order);
		assertEquals(1, rows);
	}

}
