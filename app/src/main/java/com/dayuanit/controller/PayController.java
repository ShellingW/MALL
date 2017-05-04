package com.dayuanit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.dto.AjaxResultDTO;
import com.dayuanit.mallenum.PayTypeEnum;

@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {
	
	@RequestMapping("/listPayType")
	@ResponseBody
	public AjaxResultDTO listPayType() {
		
		List<Map<String, Object>> payList = new ArrayList<Map<String, Object>>();
		
		for (PayTypeEnum pe : PayTypeEnum.values()) {
			Map<String, Object> payMap = new HashMap<String, Object>();
			payMap.put("payCode", pe.getType());
			payMap.put("payDesc", pe.getDesc());
			payList.add(payMap);
		}
		
		return AjaxResultDTO.success(payList);
	}

}
