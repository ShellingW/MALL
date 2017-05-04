<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>
<%@ page isELIgnored="false"%>

	<div class="head_top">
		<div class="wrap clearfix">
			<div class="leftArea">
			</div>
			<div class="rightArea">欢迎来到大猿商城！<a href="${cp }/user/toLogin.do">[登录]</a><a href="${cp }/user/toRegist.do">[免费注册]</a></div>
		</div>
	</div>
	<div class="search">
		<div class="wrap">
			<div class="logo">
				<a href="#"><img src="${cp }/images/logo.png" alt="大猿商城"></a>
			</div>
			<div class="search_box">
				<input type="text" id="search_input">
				<input type="submit" value="搜 索" id="search_btn">
			</div>
			<div class="shop_car">
				<span class="car">购物车</span>
				<span class="num_text">0</span>
			</div>
		</div>
	</div>
