<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>
<%@ page isELIgnored="false"%>

<div class="wrap">
	<div class="logo">
		<a href="#"><img src="${cp }/images/logo.png" alt="大猿商城"></a>
	</div>
	<h3>欢迎登录</h3>
</div>