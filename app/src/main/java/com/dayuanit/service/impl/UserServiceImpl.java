package com.dayuanit.service.impl;

import java.text.SimpleDateFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.dao.UserDao;
import com.dayuanit.domain.User;
import com.dayuanit.exception.MallBusException;
import com.dayuanit.service.UserService;
import com.dayuanit.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(UserVO userVO) throws Exception {
		
		User existUser = userDao.getUserByUsername(userVO.getUsername());
		if (null != existUser) {
			throw new MallBusException("用户已经存在");
		}
		
		if (!userVO.getConfirmPassword().equals(userVO.getPassword())) {
			throw new MallBusException("密码不相等");
		}
		
		User user = new User();
		
		if (StringUtils.isNotBlank(userVO.getBirthday())) {
			user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(userVO.getBirthday()));
		}
		
		user.setEmail(userVO.getEmail());
		user.setPassword(DigestUtils.md5Hex((userVO.getUsername() + userVO.getPassword()).getBytes()));
		user.setPhone(userVO.getPhone());
		user.setSex(userVO.getSex());
		user.setStatus(0);
		user.setUsername(userVO.getUsername());
		
		int rows = userDao.addUser(user);
		if (rows != 1) {
			throw new MallBusException("注册失败");
		}
		
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public User login(String username, String password) throws Exception {
		if (StringUtils.isBlank(username)) {
			throw new MallBusException("用户名不能为空");
		}
		
		if (StringUtils.isBlank(password)) {
			throw new MallBusException("密码不能为空");
		}
		
		User user = getUserByUsername(username);
		if (null == user) {
			throw new MallBusException("用户名错误或者密码不正确");
		}
		
		String sourceString = username + password;
		if (!user.getPassword().equals(DigestUtils.md5Hex(sourceString.getBytes()))) {
			throw new MallBusException("用户名错误或者密码不正确");
		}
		
		if (user.getStatus() == 1) {
			throw new MallBusException("用户名已冻结");
		}
		
		return user;
		
	}
	
}
