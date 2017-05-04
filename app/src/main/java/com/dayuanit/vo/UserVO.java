package com.dayuanit.vo;

public class UserVO {
	
	private String username;
	private String password;
	private String confirmPassword;
	private String phone;
	private String email;
	/**
	 * F-女 M-男
	 */
	private String sex;
	private String birthday;
	private String registCode;
	
	public String getRegistCode() {
		return registCode;
	}
	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	

}
