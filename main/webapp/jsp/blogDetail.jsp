<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>博客系统 - 博客详情</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/css/style.css">
</head>
<body>
<div class="publicHeader">
    <h1>博客系统</h1>
    <div class="publicHeaderR">
        <p>欢迎您，[用户名]</p>
        <a href="${pageContext.request.contextPath}/logout">退出登录</a>
    </div>
</div>
<div class="publicTime">
    <span id="time">当前时间：[时间]</span>
</div>
<div class="publicMian">
    <div class="left">
        <h2 class="leftH2"><span class="span1"></span>菜单<span class="span2"></span></h2>
        <ul class="list">
            <li><a href="${pageContext.request.contextPath}/blogList">博客列表</a></li>
            <li><a href="${pageContext.request.contextPath}/userInfo">个人信息</a></li>
        </ul>
    </div>
    <div class="right">
        <div class="location">
            <strong>当前位置：</strong><span>博客详情</span>
        </div>
        <div class="supplierView">
            <strong>标题：</strong><span>${blog.title}</span><br>
            <strong>作者：</strong><span>${blog.author}</span><br>
            <strong>发布时间：</strong><span>${blog.publishTime}</span><br>
            <strong>内容：</strong><span>${blog.content}</span><br>
            <a href="${pageContext.request.contextPath}/blogList">返回列表</a>
        </div>
    </div>
</div>
<div class="footer">版权所有 &copy; 博客系统</div>
</body>
</html>