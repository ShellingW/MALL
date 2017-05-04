package com.dayuanit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.dao.AddressDao;
import com.dayuanit.domain.Address;
import com.dayuanit.exception.MallBusException;
import com.dayuanit.mallenum.AddressDefualtEnum;
import com.dayuanit.mallenum.AddressStatusEnum;
import com.dayuanit.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDao addressDao;

	@Override
	public List<Map<String, String>> listProvince() {
		return addressDao.listProvince();
	}

	@Override
	public List<Map<String, String>> listCity(String provinceCode) {
		return addressDao.listCity(provinceCode);
	}

	@Override
	public List<Map<String, String>> listArea(String cityCode) {
		return addressDao.listArea(cityCode);
	}

	@Override
	public Address saveAddress(Address address) {
		
		//如果当前新增地址为默认，则将数据库默认地址改为非默认
		if (address.getDefaultFlag() == AddressDefualtEnum.ADD_DEFAULT.getFlag()) {
			Address defaultAddress = addressDao.getAddressByDefaultFlag(address.getUserId(), AddressDefualtEnum.ADD_DEFAULT.getFlag());
			if (null != defaultAddress) {
				int rows = addressDao.modifyDefault(defaultAddress.getId(), AddressDefualtEnum.ADD_NON_DEFAULT.getFlag());
				if (rows != 1) {
					throw new MallBusException("增加地址失败");
				}
			}
		}
		
		//TODO 验证用户地址个数 超过最大值不予新增 
		
		//查询省市区的名称
		Map<String, String> proMap = addressDao.getProvince(address.getProvinceCode());
		Map<String, String> cityMap = addressDao.getCity(address.getCityCode());
		Map<String, String> areaMap = addressDao.getArea(address.getAreaCode());
		
		if (null == proMap || null == cityMap || null == areaMap) {
			throw new MallBusException("地址不正确");
		}
		
		address.setProvinceName(proMap.get("name"));
		address.setCityName(cityMap.get("name"));
		address.setAreaName(areaMap.get("name"));
		
		address.setStatus(AddressStatusEnum.NORMAL.getStatus());
		
		addressDao.saveAddress(address);
		return address;
	}

	@Override
	public List<Address> listEnableAddress(Integer userId) {
		return addressDao.listAddress(userId, AddressStatusEnum.NORMAL.getStatus());
	}

}
