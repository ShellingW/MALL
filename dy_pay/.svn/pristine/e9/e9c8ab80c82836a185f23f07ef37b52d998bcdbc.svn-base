package com.dayuanit.pay.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.pay.dao.PayOrderDao;
import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.mallenum.PayStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-config-test.xml")
@Transactional("txManager")
public class PayOrderDAOTest {
	
	@Autowired
	private PayOrderDao payOrderDao;
	
	private PayOrder payOrder;
	
	@Before
	public void init() {
		payOrder = new PayOrder();
		payOrder.setAmount("1000");
		payOrder.setBankId("12000");
		payOrder.setBizId("100000");
		payOrder.setStatus(PayStatus.UNPAID.getStatus());
		payOrder.setUserId(10000);
		payOrder.setDetailMsg("麦包包");
	}
	
	@Test
	@Rollback
	public void testAddPayOrder() {
		int rows = payOrderDao.addPayOrder(payOrder);
		assertEquals(1, rows);
	}

}
