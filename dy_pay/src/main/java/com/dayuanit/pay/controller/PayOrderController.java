package com.dayuanit.pay.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.dto.AjaxResultDTO;
import com.dayuanit.pay.service.PayService;

@Controller
@RequestMapping("/payOrder")
public class PayOrderController {
	
	@Autowired
	private PayService payService;
	
	@Value("#{configProperties['dayuanit.pay.topay.url']}")
	private String payUrl;
	
	@RequestMapping("/toPay")
	public String toPay() {
		return "pay";
	}
	
	@RequestMapping("/createPayOrder")
	@ResponseBody
	public AjaxResultDTO createPayOrder(PayOrder payOrder) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("payOrderUrl", payUrl);
		map.put("payId", payOrder.getId());
		
		payService.addPayOrder(payOrder);
		return AjaxResultDTO.success(map);
	}

}
