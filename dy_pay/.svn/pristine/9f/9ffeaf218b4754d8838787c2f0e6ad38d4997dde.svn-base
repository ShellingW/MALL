package com.dayuanit.pay.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dayuanit.pay.dao.PayOrderDao;
import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.service.PayService;

@Service
@WebService(endpointInterface = "com.dayuanit.pay.service.PayService")
public class PayServiceImpl implements PayService {
	
	@Value("#{configProperties['dayuanit.pay.topay.url']}")
	private String payUrl;
	
	@Autowired
	private PayOrderDao payOrderDao;

	
	@Override
	public Map<String, Object> addPayOrder(PayOrder payOrder) {
		payOrderDao.addPayOrder(payOrder);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("payOrderUrl", payUrl);
		map.put("payId", payOrder.getId());
		return map;
	}

}
