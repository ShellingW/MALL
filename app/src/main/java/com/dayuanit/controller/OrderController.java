package com.dayuanit.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.dto.AjaxResultDTO;
import com.dayuanit.dto.SettleInfoDTO;
import com.dayuanit.exception.MallBusException;
import com.dayuanit.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/toOrder")
	public String toOrder(int goodId, int num, String token, HttpServletRequest request, HttpSession session) {
		Object obj = session.getAttribute("orderToken");
		if (null != obj) {
			if (obj.toString().equals(token)) {
				return "home.page";
			}
		}
		
		String submitToken = UUID.randomUUID().toString();
		session.setAttribute("submitToken", submitToken);
		
		SettleInfoDTO sid = orderService.settleInfo(goodId, num);
		request.setAttribute("settleInfo", sid);
		
		return "order.page";
	}
	
	@RequestMapping("/saveOrder")
	@ResponseBody
	public AjaxResultDTO saveOrder(String token, int num, int goodId, int addressId, int payType, HttpServletRequest request, HttpSession session) {
		Map<String, String> map = null;
		
		try {
			Object obj = session.getAttribute("submitToken");
			if (null == obj) {
				return AjaxResultDTO.failed("非法请求");
			} else {
				String submitToken = obj.toString();
				if (!submitToken.equals(token)) {
					return AjaxResultDTO.failed("请求过期");
				}
			}
			
			session.removeAttribute("submitToken");
			
			map = new HashMap<String, String>();
			map.put("payOrderUrl", orderService.addOrder(goodId, num, getUserId(request), addressId, payType));
		} catch(MallBusException me) {
			logger.error(me.getMessage(), me);
		} catch(Exception e) {
			logger.error("提交订单失败", e);
		}
		
		return AjaxResultDTO.success(map);
	}

}
