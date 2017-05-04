package com.dayuanit.service;

import com.dayuanit.domain.User;
import com.dayuanit.vo.UserVO;

public interface UserService {
	
	void addUser(UserVO userVO) throws Exception;
	
	User getUserByUsername(String username);
	
	User login(String username, String password) throws Exception;

}
