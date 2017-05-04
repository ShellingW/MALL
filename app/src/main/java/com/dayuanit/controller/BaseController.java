package com.dayuanit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dayuanit.constant.LoginConstant;
import com.dayuanit.domain.User;

public abstract class BaseController {
	protected final static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected Integer getUserId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(LoginConstant.LOGIN_FLAG);
		if (null != obj) {
			User user = (User)obj;
			return user.getId();
		}
		return 9;
	}
	
}
