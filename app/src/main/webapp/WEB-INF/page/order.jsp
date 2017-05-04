<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>

<link rel="stylesheet" href="${cp }/css/order/style.css" type="text/css">
<link rel="stylesheet" href="${cp }/css/order/reset.css" type="text/css">

<div class="account wrap">
	<div class="account_box receive">
		<h3>收货信息</h3>
		<ul id="showAddressUL">
		</ul>
		<input class="account_btn blue_btn" type="button" value="新增收获地址" onclick="toAddress();">
	</div>
	<div class="account_box pay">
		<h3>支付方式</h3>
		<ul id="payTypeUL">
		</ul>
	</div>
	<div class="account_box deliver">
		<h3>送货清单<a href="#">返回购物车修改</a></h3>
		<ul class="clearfix">
			<li class="deliver_width_440">商品名称</li>
			<li class="deliver_width_135">单价</li>
			<li class="deliver_width_135">数量</li>
			<li>小计</li>
		</ul>
		<div class="order_info clearfix">
			<div class="order_name deliver_width_440 clearfix">
				<div class="order_img"><img src="${cp }/css/home/images/NFC.jpg" alt=""></div>
				<div class="order_text">
					<h4>${settleInfo.goodTile }</h4>
					<p>${settleInfo.detail }</p>
				</div>
			</div>
			<div class="order_price deliver_width_135">￥${settleInfo.unitPrice }</div>
			<div class="commodity_num deliver_width_135"><p>${settleInfo.num }</p></div>
			<div class="count">￥${settleInfo.amount }</div>
		</div>
	</div>
	<div class="account_box total">
		<h3>订单结算</h3>
		<p>总计<span>￥${settleInfo.amount }</span></p>
		<div class="submit_btn clearfix"><input type="button" value="提交订单" onclick="submitOrder();"></div>
	</div>
	
	<div id="addressDiv">
		<select id="provinceSel" onchange="provinceChange();">
		</select><br><br>
		<select id="citySel" onchange="cityChange();" >
			<option value="0">==城市==</option>
		</select><br><br>
		<select id="areaSel">
			<option value="0">==地区==</option>
		</select><br>
		详细地址：<input type="text" id="detailAddress"><br><br>
		收货人：<input type="text" id="username"><br><br>
		手机号码：<input type="text" id="phone"><br><br>
		默认地址：<input type="radio" value="1" id="defAddId2" name="defAdd">是&nbsp;&nbsp;
			   <input type="radio" value="0" id="defAddId" name="defAdd" checked="checked">否
	</div>
</div>

<script type="text/javascript">

var addressFlag = true;
var addressArray = [];
var num = ${settleInfo.num};
var amount = ${settleInfo.amount};
var goodId = ${settleInfo.goodId};
var token = '${submitToken}';

$(document).ready(function() {
	
	queryPrivince();
	initSaveAddress();
	listAddress();
	loadPayType();
});

function loadPayType() {
	$.ajax({
		 url:'${cp}/pay/listPayType.do',
		    type:'POST', //GET
		    data:{
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	if(!data.success) {
		    		alert(data.message);
		    		return false;
		    	}
		    	
		    	var results = data.data;
		    	var payHtml = '';
		    	for(var i=0;i<results.length;i++) {
		    		var result = results[i];
		    		payHtml += '<li>';
		    		payHtml += '<input type="radio" name="pay_way" value="'+result.payCode+'">';
		    		payHtml += '<label for="weixin_pay">';
		    		payHtml += result.payDesc;
		    		payHtml += '</label>';
		    		payHtml += '</li>';
		    	}
		    	
		    	$('#payTypeUL').html(payHtml);
		    	
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束');
		        addressFlag = true;
		    }
	});
}

function submitOrder() {
	$.ajax({
		 url:'${cp}/order/saveOrder.do',
		    type:'POST', //GET
		    data:{
		    	//int num, int goodId, String address, int payType
		    	num : num,
		    	goodId : goodId,
		    	payType : $("input:radio[name='pay_way']:checked").val(),
		    	addressId : $("input:radio[name='displayAddress']:checked").val(),
		    	token : token
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	if(!data.success) {
		    		alert(data.message);
		    		return false;
		    	}
		    	
		    	alert("恭喜你，订单完成!");
		    	window.location.href = data.data.payOrderUrl;
		    	
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束');
		        addressFlag = true;
		    }
	});
}

function listAddress() {
	$.ajax({
		 url:'${cp}/address/listAddress.do',
		    type:'POST', //GET
		    data:{
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	if(!data.success) {
		    		alert(data.message);
		    		return false;
		    	}
		    	
		    	addressArray = data.data;
		    	showAddress();
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束');
		        addressFlag = true;
		    }
	});
}

function showAddress() {
	var addHtml = '';
	for (var i=0;i<addressArray.length;i++){
		var result = addressArray[i];
		addHtml += '<li>';
		if(1 == result.defaultFlag) {
			addHtml += '<input type="radio" name="displayAddress" value="'+result.id+'" checked="checked">&nbsp;';
		} else {
			addHtml += '<input type="radio" name="displayAddress" value="'+result.id+'">&nbsp;';
		}
		
		addHtml += '<span>';
		addHtml += result.realName 
					+ '&nbsp;' 
					+ result.phone 
					+ '&nbsp;' 
					+ result.provinceName 
					+ '&nbsp;' 
					+ result.cityName 
					+ '&nbsp;' 
					+ result.areaName 
					+ '&nbsp;' 
					+ result.detail;
		addHtml += '</span>';
		addHtml += '</li>';
	}
	
	$('#showAddressUL').html(addHtml);
}

function initSaveAddress() {
		$(function() {
		    $( "#addressDiv" ).dialog({
		      resizable: false,
		      autoOpen: false,
		      height:300,
		      modal: true,
		      buttons: {
		        "保存": function() {
		        	saveAddress();
		        },
		        '取消': function() {
		          $( this ).dialog( "close" );
		        }
		      },
		      close : function() {
		    	 	$("#provinceSel").val("0");
		    		$('#citySel').html('<option value="0">==城市==</option>');
		    		$('#areaSel').html('<option value="0">==地区==</option>');
		    		$('#detailAddress').val('');
		    		$('#username').val('');
		    		$('#phone').val('');
		    	  	document.getElementById("defAddId").checked = true;
		      },
		      open : function() {
		      }
		    });
	});
}

function saveAddress() {
	if (addressFlag == false) {
		return false;
	}
	
	addressFlag = false;
	
	$.ajax({
		 url:'${cp}/address/saveAdddress.do',
		    type:'POST', //GET
		    data:{
		    	provinceCode : $('#provinceSel').val(),
		    	cityCode : $('#citySel').val(),
		    	areaCode : $('#areaSel').val(),
		    	detail : $('#detailAddress').val(),
		    	realName : $('#username').val(),
		    	phone : $('#phone').val(),
		    	defaultFlag : $("input:radio[name='defAdd']:checked").val()
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	if(!data.success) {
		    		alert(data.message);
		    		return false;
		    	}
		    	
		    	addressArray.push(data.data);
		    	showAddress();
		    	$( '#addressDiv' ).dialog( "close" );
		    	
		    },
		    error:function(xhr,textStatus){
		        console.log('错误')
		        console.log(xhr)
		        console.log(textStatus)
		    },
		    complete:function(){
		        console.log('结束');
		        addressFlag = true;
		    }
	});
}

function cityChange() {
	var cityCode = $('#citySel').val();
	if (cityCode == 0) {
		$('#citySel').html('<option value="0">==地区==</option>');
		return false;
	}
	
	$.ajax({
		 url:'${cp}/address/listArea.do',
		    type:'POST', //GET
		    data:{
		    	cityCode : cityCode
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	if(!data.success) {
		    		alert(data.message);
		    		return false;
		    	}
		    	
		    	var results = data.data;
		    	var opt = '';
		    	
		    	for (var i=0; i<results.length; i++) {
		    		var result = results[i];
		    		opt += '<option value="'+result.code+'">'+ result.name +'</option>';
		    	}
		    	
		    	$('#areaSel').html(opt);
		    	
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

function provinceChange() {
	var proCode = $('#provinceSel').val();
	if (proCode == 0) {
		$('#citySel').html('<option value="0">==城市==</option>');
		$('#areaSel').html('<option value="0">==地区==</option>');
		return false;
	}
	
	$.ajax({
		 url:'${cp}/address/listCity.do',
		    type:'POST', //GET
		    data:{
		    	provinceCode : proCode
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	if(!data.success) {
		    		alert(data.message);
		    		return false;
		    	}
		    	
		    	var results = data.data;
		    	var opt = '';
		    	
		    	for (var i=0; i<results.length; i++) {
		    		var result = results[i];
		    		opt += '<option value="'+result.code+'">'+ result.name +'</option>';
		    	}
		    	
		    	$('#citySel').html(opt);
		    	cityChange();
		    	
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

function queryPrivince() {
	
	$.ajax({
		 url:'${cp}/address/listProvince.do',
		    type:'POST', //GET
		    data:{
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	if(!data.success) {
		    		alert(data.message);
		    		return false;
		    	}
		    	
		    	var results = data.data;
		    	var opt = '<option value="0">==省份==</option>';
		    	for (var i=0; i<results.length; i++) {
		    		var result = results[i];
		    		opt += '<option value="'+result.code+'">'+ result.name +'</option>';
		    	}
		    	
		    	$('#provinceSel').html(opt);
		    	
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

function toAddress() {
	$( "#addressDiv" ).dialog( "open" );
}

</script>