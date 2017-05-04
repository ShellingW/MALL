package com.dayuanit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.domain.Address;
import com.dayuanit.dto.AjaxResultDTO;
import com.dayuanit.exception.MallBusException;
import com.dayuanit.service.AddressService;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping("/listProvince")
	@ResponseBody
	public AjaxResultDTO listProvince() {
		return AjaxResultDTO.success(addressService.listProvince());
	}
	
	@RequestMapping("/listCity")
	@ResponseBody
	public AjaxResultDTO listCity(String provinceCode) {
		return AjaxResultDTO.success(addressService.listCity(provinceCode));
	}
	
	@RequestMapping("/listArea")
	@ResponseBody
	public AjaxResultDTO listArea(String cityCode) {
		return AjaxResultDTO.success(addressService.listArea(cityCode));
	}
	
	@RequestMapping("/saveAdddress")
	@ResponseBody
	public AjaxResultDTO saveAdddress(Address address, HttpServletRequest request) {
		try {
			address.setUserId(getUserId(request));
			address = addressService.saveAddress(address);
		} catch (MallBusException me) {
			logger.error(me.getMessage(), me);
			return AjaxResultDTO.failed(me.getMessage());
		} catch (Exception e) {
			logger.error("新增地址失败，{}", e.getMessage(), e);
			return AjaxResultDTO.failed("新增地址失败");
		}
		
		return AjaxResultDTO.success(address);
	}
	
	@RequestMapping("/listAddress")
	@ResponseBody
	public AjaxResultDTO listAddress(HttpServletRequest request) {
		return AjaxResultDTO.success(addressService.listEnableAddress(getUserId(request)));
	}

}
