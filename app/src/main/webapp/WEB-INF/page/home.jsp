<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>

<link rel="stylesheet" href="${cp }/css/home/css/style.css" type="text/css">
<link rel="stylesheet" href="${cp }/css/home/css/reset.css" type="text/css">
	
<div id="homeGoods"></div>

<script type="text/javascript">
	$(document).ready(function(){
		loadMenu();
	});
	
	var menus = [];
	
	function loadMenu() {
		$.ajax({
		    url:'${cp }/menu/loadMenus.do',
		    type:'POST', //GET
		    async:true,
		    data:{
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	if (data.success) {
		    		var results = data.data;
		    		for (var i=0;i<results.length;i++) {
		    			menus.push(results[i]);
		    		}
		    		
		    		loadGoods(menus);
		    		
		    	} else {
		    		alert(data.message);
		    	}
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束')
		    }
		});
	}
	
	
	function loadGoods(menus) {
		
		var goodsHtml = '';
		for (var i=0; i<menus.length;i++) {
			var menu = menus[i];
			$.ajax({
			    url:'${cp }/good/listGoods.do',
			    type:'POST', //GET
			    async:false,
			    data:{
			    	menuId : menus[i].id
			    },
			    timeout:5000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data){
			    	if (data.success) {
			    		var results = data.data;
			    		goodsHtml += '<div class="shopArea">'
							+ '<div class="wrap">'
							+ '<div class="shop_title">'
							+ '<h3>' + menu.menuName + '</h3>'
							+ '<span>更多&gt;&gt;</span>'
							+ '</div>'
							+ '<div class="main clearfix">'
							+ '<div class="shop_banner">'
							+ '<img src="${cp }/css/home/images/ad01.jpg" alt="shop_banner">'
							+ '</div>'
							+ '<div class="shop_list">'
							+ '<div class="shoplist_box clearfix">';
			    		for (var i=0;i<results.length;i++) {
			    			var result = results[i];
			    						if (i < 4) {
			    							goodsHtml += '<div class="shopItem_top">'
							    						+ '<div class="shop_img"><a href="${cp }/good/toDetail.do?goodId='+result.id+'" target="_blank"><img src="${cp }/css/home/images/HTC.jpg" alt="商品"></a></div>'
							    						+ '<h4>'+result.name+'</h4>'
							    						+ '<p>'+result.price+'元</p>'
							    						+ '</div>';
			    						} else {
			    							goodsHtml += '<div class="shopItem_bottom">'
			    										+ '<div class="shop_img"><a href="${cp }/good/toDetail.do?goodId='+result.id+'" target="_blank"><img src="${cp }/css/home/images/NFC.jpg" alt="商品"></a></div>'
			    										+ '<div class="shop_text">'
			    										+ '<p>'+result.name+'</p>'
			    										+ '<span>￥'+result.price+'</span>'
			    										+ '</div>'
			    										+ '</div>';
			    						}
			    		}
			    		
			    		goodsHtml += '</div></div></div></div></div>';
						$('#homeGoods').html(goodsHtml);
			    		
			    	} else {
			    		alert(data.message);
			    	}
			    },
			    error:function(xhr,textStatus){
			        console.log('错误')
			        console.log(xhr)
			        console.log(textStatus)
			    },
			    complete:function(){
			        console.log('结束')
			    }
			});
		}
	}
</script>