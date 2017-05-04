<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<c:set var="cp" scope="session" value="${pageContext.request.servletContext.contextPath}"/>

<link rel="stylesheet" href="${cp }/css/reset.css" type="text/css">
<link rel="stylesheet" href="${cp }/css/style.css" type="text/css">
	
<div class="register">
	<ul class="register_box">
		<li class="user_info"><span><b>*</b>账户名：</span><input type="text" placeholder="用户名" id="username" class="login_name"></li>
		<li class="user_info"><span><b>*</b>请设置密码：</span><input type="password" id="password" class="login_password"></li>
		<li class="user_info"><span><b>*</b>其确认密码吗：</span><input type="password" id="confirmPassword" class="login_password"></li>
		<li class="user_info"><span><b>*</b> 邮箱：</span><input type="text" id="email"></li>
		<li class="user_info"><span><b>*</b> 性别：</span><input type="radio" name="sex" value="F" checked="checked">女&nbsp; <input type="radio" name="sex" value="M">男</li>
		<li class="user_info"><span><b>*</b> 手机：</span><input type="text" id="phone"></li>
		<li class="user_info"><span><b>*</b> 生日：</span><input type="text" id="birthday"></li>
		<li class="user_info"><span><b>*</b> 验证码：</span><input type="text" id="registCode"></li>
		<li class="user_info"><span><b>*</b> </span><img id="codeImg" alt="验证码" src="${cp }/code/geneCode.do" onclick="loadCodeImag();"></li>
		<li class="agree"><input type="checkbox" checked="checked" id="agreement"><label for="agreement">我已阅读并同意</label><a href="#">《用户注册协议》</a></li>
		<li class="submit"><input type="button" value="提交" onclick="regist();" class="submit_btn"></li>
	</ul>
</div>
<script type="text/javascript">

	function loadCodeImag() {
		$("#codeImg").attr('src','${cp }/code/geneCode.do?' + Math.random());
	}
	
	function regist() {
		$.ajax({
		    url:'${cp }/user/regist.do',
		    type:'POST', //GET
		    async:false,
		    data:{
		    	username : $('#username').val(),
		    	password : $('#password').val(),
		    	confirmPassword : $('#confirmPassword').val(),
		    	phone : $('#phone').val(),
		    	email : $('#email').val(),
		    	sex : $("input[name='sex'][checked]").val(),
		    	birthday : $('#birthday').val(),
		    	registCode : $('#registCode').val()
		    },
		    timeout:5000,    //超时时间
		    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
		    success:function(data){
		    	
		    	if (data.success) {
		    		alert('注册成功');
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