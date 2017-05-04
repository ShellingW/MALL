package com.dayuanit.service;

import java.util.List;
import java.util.Map;

import com.dayuanit.domain.Address;

public interface AddressService {
	
	List<Map<String, String>> listProvince();
	
	List<Map<String, String>> listCity(String provinceCode);
	
	List<Map<String, String>> listArea(String cityCode);
	
	Address saveAddress(Address address);
	
	List<Address> listEnableAddress(Integer userId);

}
