package com.dayuanit.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.dao.AddressDao;
import com.dayuanit.dao.GoodDao;
import com.dayuanit.dao.OrderDao;
import com.dayuanit.domain.Address;
import com.dayuanit.domain.Good;
import com.dayuanit.domain.Order;
import com.dayuanit.dto.SettleInfoDTO;
import com.dayuanit.exception.MallBusException;
import com.dayuanit.mallenum.OrderEnum;
import com.dayuanit.service.OrderService;
import com.dayuanit.util.DateUtil;
import com.dayuanit.util.PriceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private GoodDao goodDao;
	
	@Autowired
	private AddressDao addressDao;

	@Override
	public String addOrder(int goodId, int num, int userId, int addressId, int payType) {
		Order order = new Order();
		
		Good good = goodDao.getGood(goodId);
		Address address = addressDao.getAddressById(addressId);
		
		String amount = PriceUtil.mul(good.getPrice(), String.valueOf(num));
		order.setAmount(amount);
		order.setDeadTime(DateUtil.add(new Date(), 30));
		order.setGoodId(goodId);
		order.setNum(num);
		order.setStatus(OrderEnum.未支付.getStatus());
		order.setUnitPrice(good.getPrice());
		order.setUserId(userId);
		order.setAddress(address.getProvinceName() + "$" + address.getCityName() + "$" + address.getAreaName() + "$" + address.getDetail() + "$" + address.getRealName() + "$" + address.getPhone());
		order.setPayType(payType);
		
		int rows = orderDao.addOrder(order);
		if (rows != 1){
			throw new MallBusException("订单提交失败");
		}
		
		//TODO 访问支付系统，获取支付页面地址
		return requestPaySystem(order);
	}
	
	@Override
	public SettleInfoDTO settleInfo(Integer goodId, Integer num) {
		Good good = goodDao.getGood(goodId);
		String amount = PriceUtil.mul(good.getPrice(), String.valueOf(num));
		
		SettleInfoDTO sid = new SettleInfoDTO();
		sid.setAmount(amount);
		sid.setDetail(good.getDetail());
		sid.setGoodTile(good.getName());
		sid.setImageUrl(good.getImageUrl());
		sid.setNum(num);
		sid.setUnitPrice(good.getPrice());
		sid.setGoodId(good.getId());
		return sid;
	}
	
	private String requestPaySystem(Order order) {
		try {
			URL url = new URL("http://pay.dayuanit.com:8088/payOrder/createPayOrder.do");
			URLConnection rulConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
			 httpUrlConnection.setDoOutput(true);
			 httpUrlConnection.setDoInput(true);
			 httpUrlConnection.setUseCaches(false);
			 httpUrlConnection.setRequestMethod("POST");
			 httpUrlConnection.connect();
			 
			 String param = "amount=" + order.getAmount() + "&userId="+ order.getUserId() + "&bizId="+order.getId() + "&detailMsg=" + URLEncoder.encode("订单系统", "utf-8");
			 
			 DataOutputStream dos=new DataOutputStream(httpUrlConnection.getOutputStream());
			 dos.writeBytes(param);
			 dos.flush();
			 dos.close();
			    
			 InputStream inStrm = httpUrlConnection.getInputStream();
			 BufferedReader bf = new BufferedReader(new InputStreamReader(inStrm, "utf-8"));
			 String msg = "";
			 String tmp = null;
			 while (null != (tmp = bf.readLine())) {
				 msg += tmp;
			 }
			 
			 System.out.println(msg);
			 ObjectMapper mapper = new ObjectMapper();
			String targetUrl = mapper.readTree(msg).findValue("data").findValue("payOrderUrl").asText();
			 return targetUrl;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:8088/payOrder/createPayOrder.do");
			URLConnection rulConnection = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;
			 httpUrlConnection.setDoOutput(true);
			 httpUrlConnection.setDoInput(true);
			 httpUrlConnection.setUseCaches(false);
			 httpUrlConnection.setRequestMethod("POST");
			 httpUrlConnection.connect();
			 InputStream inStrm = httpUrlConnection.getInputStream();
			 BufferedReader bf = new BufferedReader(new InputStreamReader(inStrm, "utf-8"));
			 String msg = "";
			 String tmp = null;
			 while (null != (tmp = bf.readLine())) {
				 msg += tmp;
			 }
			 
			 System.out.println(msg);
			 ObjectMapper mapper = new ObjectMapper();
			String targetUrl = mapper.readTree(msg).findValue("data").findValue("payOrderUrl").asText();
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}

}
