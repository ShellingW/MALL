package com.dayuanit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.dto.AjaxResultDTO;
import com.dayuanit.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/loadMenus")
	@ResponseBody
	public AjaxResultDTO loadMenus() {
		return AjaxResultDTO.success(menuService.listMenus());
	}

}
