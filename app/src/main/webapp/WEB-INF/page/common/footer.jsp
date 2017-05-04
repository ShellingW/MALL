<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>
<%@ page isELIgnored="false"%>

	<p>客服热线：400-675-1234</p>
	<p>Copyright © 2016 - 2017 大猿软件版权所有   苏 ICP 备 11059958号-5</p>
	<div class="credit_rating">
		<a href="#"><img src="${cp }/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="${cp }/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="${cp }/images/pj.jpg" alt="信用评价"></a>
		<a href="#"><img src="${cp }/images/pj.jpg" alt="信用评价"></a>
	</div>
