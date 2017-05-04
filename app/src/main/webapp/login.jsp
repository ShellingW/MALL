<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>

<link rel="stylesheet" href="${cp }/css/login/reset.css" type="text/css">
<link rel="stylesheet" href="${cp }/css/login/style.css" type="text/css">
	
<div class="login">
	<ul class="login_box">
		<li class="login_text">用户名</li>
		<li><input type="text" class="user_name" id="username"></li>
		<li class="login_text">密码</li>
		<li><input type="password" class="user_password" id="password"></li>
		<li class="login_check">
			<input type="checkbox" id="auto_login"><label for="auto_login">自动登录</label>
			<a href="###">忘记密码？</a>
		</li>
		<li><input class="login_btn" type="button" value="登录" onclick="login();"></li>
	</ul>
</div>
<div class="wrap register">
	<a href="#" class="free_register">免费注册&gt;&gt;</a></div>

<script type="text/javascript">
	function login() {
		$.ajax({
		    url:'user/login.do',
		    type:'POST', //GET
		    async:false,
		    data:{
		    	username : $('#username').val(),
		    	password : $('#password').val()
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	
		    	if (data.success) {
		    		alert('登录成功');
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
</script>