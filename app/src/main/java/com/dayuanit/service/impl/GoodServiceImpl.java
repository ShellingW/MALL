package com.dayuanit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.dao.GoodDao;
import com.dayuanit.domain.Good;
import com.dayuanit.service.GoodService;

@Service
public class GoodServiceImpl implements GoodService {
	
	@Autowired
	private GoodDao goodDao;

	@Override
	public List<Good> listGoods(int menuId) {
		return goodDao.listGoodsByMenuId(menuId);
	}

	@Override
	public Good getGoods(int goodId) {
		return goodDao.getGood(goodId);
	}

}
