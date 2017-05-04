package com.dayuanit.dao;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.domain.User;

public interface UserDao {
	int addUser(User user);
	
	User getUserByUsername(@Param("usrename") String usrename);

}
