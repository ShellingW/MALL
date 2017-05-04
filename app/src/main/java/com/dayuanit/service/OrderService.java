package com.dayuanit.service;

import com.dayuanit.domain.Order;
import com.dayuanit.dto.SettleInfoDTO;

public interface OrderService {

	String addOrder(int goodId, int num, int userId, int addressId, int payType);
	
	SettleInfoDTO settleInfo(Integer goodId, Integer num);
}
