package com.dayuanit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.domain.Good;

public interface GoodDao {
	
	List<Good> listGoodsByMenuId(@Param("menuId") Integer menuId);
	
	Good getGood(@Param("goodId") Integer goodId);

}
