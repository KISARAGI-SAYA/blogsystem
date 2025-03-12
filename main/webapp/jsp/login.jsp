<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>博客系统 - 登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/style.css">
</head>
<body class="login_bg">
<div class="loginBox">
    <div class="loginHeader">
        <h1>博客系统</h1>
    </div>
    <div class="loginCont">
        <form class="loginForm" action="${pageContext.request.contextPath}/login" method="post">
            <div class="inputbox">
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username" placeholder="请输入用户名">
            </div>
            <div class="inputbox">
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" placeholder="请输入密码">
            </div>
            <div class="subBtn">
                <input type="submit" value="登录">
                <input type="reset" value="重置">
            </div>
        </form>
    </div>
</div>
</body>
</html>