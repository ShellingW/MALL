package com.dayuanit.service;

import java.util.List;

import com.dayuanit.domain.Good;

public interface GoodService {
	
	List<Good> listGoods(int menuId);
	
	Good getGoods(int goodId);

}
