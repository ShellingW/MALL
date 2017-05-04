<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>
	
	<div class="head_top">
		<div class="wrap clearfix">
			<div class="leftArea">
			</div>
			<div class="rightArea">欢迎来到京西网！<a href="#">[登录]</a><a href="#">[免费注册]</a></div>
		</div>
	</div>
	<div class="search">
		<div class="wrap">
			<div class="logo">
				<a href="#"><img src="${cp }/images/logo.png" alt="京西商城"></a>
			</div>
			<div class="progress_bar">
				<img src="${cp }/images/order/progress_bar.png" alt="进度条">
				<ul class="clearfix">
					<li class="progress_third">订单提交成功</li>
					<li class="progress_second">填写核对订单</li>
					<li class="progress_first">我的购物车</li>
				</ul>
			</div>
		</div>
	</div>