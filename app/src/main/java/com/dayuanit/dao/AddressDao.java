package com.dayuanit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.domain.Address;

public interface AddressDao {
	
	List<Map<String, String>> listProvince();
	
	List<Map<String, String>> listCity(@Param("provincecode") String provincecode);
	
	List<Map<String, String>> listArea(@Param("cityCode") String cityCode);
	
	int saveAddress(Address address);
	
	int modifyDefault(@Param("id")Integer id, @Param("defaultFlag") Integer defaultFlag);
	
	Address getAddressByDefaultFlag(@Param("userId")Integer userId, @Param("defaultFlag") Integer defaultFlag);
	
	Map<String, String> getProvince(@Param("provinceCode")String provinceCode);
	
	Map<String, String> getCity(@Param("cityCode")String cityCode);
	
	Map<String, String> getArea(@Param("areaCode")String areaCode);
	
	List<Address> listAddress(@Param("userId") Integer userId, @Param("status") Integer status);
	
	Address getAddressById(@Param("addressId")Integer addressId);

}
