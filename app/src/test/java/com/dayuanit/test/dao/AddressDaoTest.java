package com.dayuanit.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.dao.AddressDao;
import com.dayuanit.domain.Address;
import com.dayuanit.mallenum.AddressStatusEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/spring-config-test.xml")
@Transactional("txManager")
public class AddressDaoTest {
	
	@Autowired
	private AddressDao addressDao;
	
	private Address address;
	private Address address2;
	
	@Before
	public void init() {
		address = new Address();
		address.setAreaCode("130424");
		address.setAreaName("成安县");
		address.setCityCode("130400");
		address.setCityName("邯郸市");
		address.setDefaultFlag(1);
		address.setDetail("大明小区");
		address.setPhone("138000000");
		address.setProvinceCode("130000");
		address.setProvinceName("河北省");
		address.setRealName("小敏");
		address.setStatus(AddressStatusEnum.INVALID.getStatus());
		address.setUserId(100001);
		
		address2 = new Address();
		address2.setAreaCode("130424");
		address2.setAreaName("成安县");
		address2.setCityCode("130400");
		address2.setCityName("邯郸市");
		address2.setDefaultFlag(1);
		address2.setDetail("大明小区");
		address2.setPhone("138000000");
		address2.setProvinceCode("130000");
		address2.setProvinceName("河北省");
		address2.setRealName("小敏");
		address2.setStatus(AddressStatusEnum.NORMAL.getStatus());
		address2.setUserId(100001);
	}
	
	
	@Test
	@Rollback
	public void testSaveAddress() {
		int rows = addressDao.saveAddress(address);
		assertEquals(1, rows);
	}
	
	@Test
	@Rollback
	public void testModifyDefault() {
		addressDao.saveAddress(address);
		int rows = addressDao.modifyDefault(address.getId(), 0);
		assertEquals(1, rows);
	}
	
	@Test
	@Rollback
	public void testGetAddressByDefaultFlag() {
		addressDao.saveAddress(address);
		Address loadAddress = addressDao.getAddressByDefaultFlag(address.getUserId(), address.getDefaultFlag());
		assertEquals(address.getId(), loadAddress.getId());
	}
	
	@Test
	@Rollback
	public void testListAddress() {
		addressDao.saveAddress(address);
		addressDao.saveAddress(address2);
		List<Address> list = addressDao.listAddress(100001, AddressStatusEnum.NORMAL.getStatus());
		assertEquals(1, list.size());
	}

}
