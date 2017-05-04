package com.dayuanit.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.dto.AjaxResultDTO;
import com.dayuanit.service.GoodService;

@Controller
@RequestMapping("/good")
public class GoodController {
	
	@Autowired
	private GoodService goodService;
	
	@RequestMapping("/listGoods")
	@ResponseBody
	public AjaxResultDTO listGoods(int menuId) {
		return AjaxResultDTO.success(goodService.listGoods(menuId));
	}
	
	@RequestMapping("toDetail")
	public String toDetail(int goodId, HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		request.setAttribute("good", goodService.getGoods(goodId));
		request.setAttribute("orderToken", token);
		return "goodDetail.page";
	}

}
