<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${cp}/css/jquery-ui.min.css">
<script type="text/javascript" src="${cp }/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${cp}/js/jquery-ui.min.js"></script>

<!DOCTYPE html>
<html>
	<div id="head">
		<tiles:insertAttribute name="header"/>
	</div>
	
	<body>
		<tiles:insertAttribute name="body"/>
		
		<div id="footer">
			<tiles:insertAttribute name="footer"/>
		</div>
	</body>
</html>
