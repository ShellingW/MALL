<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>

<link rel="stylesheet" href="${cp }/css/detail/css/reset.css" type="text/css">
<link rel="stylesheet" href="${cp }/css/detail/css/style.css" type="text/css">
	
<div class="bread wrap"><a class="index" href="#">首页</a><em>&gt;</em><a href="#">平板电脑</a>&gt;<a href="#">Apple 苹果</a>&gt;<a href="#">MD531CH/A</a></div>
<div class="commodity_info wrap clearfix">

<input type="hidden" value="${orderToken }">
	<div class="info_left">
		<div class="commodity_img"><img src="${cp }/css/detail/images/sp.jpg" alt="商品图片"></div>
	</div>
	<div class="info_right">
		<h3 class="shop_name">${good.name }</h3>
		<dl class="price">
			<dt>京西价</dt>
			<dd><b>￥</b>${good.price }</dd>
		</dl>
		<div class="selection">
			<dl class="specification">
				<dt>商品描述</dt>
				<dd class="clearfix">
					${good.detail }
				</dd>
			</dl>
			<dl>
				<dt>数量</dt>
				<dd class="clearfix">
					<div class="num_select">
						<span>${good.num }</span>
					</div>
				</dd>
			</dl>
		</div>
		<div class="buy">
			<div class="buy_btn">
				<a href="#">加入购物车</a>
				<span class="ver_line"></span>
				<a href="${cp }/order/toOrder.do?goodId=${good.id }&num=2&token=${orderToken}">立即购买</a>
			</div>
			<p class="notice">注意：此商品可提供普通发票，不提供增值税发票。</p>
		</div>
	</div>
</div>

</html>