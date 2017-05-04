package com.dayuanit.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.constant.LoginConstant;
import com.dayuanit.domain.User;
import com.dayuanit.dto.AjaxResultDTO;
import com.dayuanit.exception.MallBusException;
import com.dayuanit.service.UserService;
import com.dayuanit.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login.page";
	}
	
	@RequestMapping("/toRegist")
	public String toRegist() {
		return "regist.page";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public AjaxResultDTO login(String username, String password, HttpSession session) {
		try {
			User user = userService.login(username, password);
			session.setAttribute(LoginConstant.LOGIN_FLAG, user);
		} catch (MallBusException me) {
			logger.error("登录异常:{}", me.getMessage(), me);
			return AjaxResultDTO.failed(me.getMessage());
		} catch (Exception e) {
			logger.error("登录异常:{}", e.getMessage(), e);
			return AjaxResultDTO.failed("登录失败");
		}
		
		return AjaxResultDTO.success();
	}
	
	@RequestMapping("/regist")
	@ResponseBody
	public AjaxResultDTO regist(UserVO userVO, HttpSession session) {
		
		try {
			String sessionCode = (String)session.getAttribute(CodeController.CODE_FLAG);
			if (!sessionCode.equalsIgnoreCase(userVO.getRegistCode())) {
				return AjaxResultDTO.failed("验证码无效");
			}
			userService.addUser(userVO);
		} catch (MallBusException me) {
			logger.error("注册异常:{}", me.getMessage(), me);
			return AjaxResultDTO.failed(me.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("注册异常:{}", e.getMessage(), e);
			return AjaxResultDTO.failed("注册失败");
		} finally {
			session.removeAttribute(CodeController.CODE_FLAG);
		}
		
		return AjaxResultDTO.success();
		
	}
	
	public static void main(String[] args) {
		String pwd = "1234566";
		
		System.out.println(DigestUtils.md5Hex(pwd.getBytes()));
	}

}
