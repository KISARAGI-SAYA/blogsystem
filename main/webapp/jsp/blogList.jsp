<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>博客系统 - 博客列表</title>
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
    <a href="${pageContext.request.contextPath}/addBlog">新增博客</a>
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
            <strong>当前位置：</strong><span>博客列表</span>
        </div>
        <div class="search">
            <input type="text" placeholder="请输入关键词">
            <input type="button" value="搜索">
        </div>
        <table class="supplierTable">
            <tr class="firstTr">
                <th>标题</th>
                <th>作者</th>
                <th>发布时间</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${blogList}" var="blog">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/blogDetail?id=${blog.id}">${blog.title}</a></td>
                    <td>${blog.author}</td>
                    <td>${blog.publishTime}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/editBlog?id=${blog.id}">编辑</a>
                        <a href="${pageContext.request.contextPath}/deleteBlog?id=${blog.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="footer">版权所有 &copy; 博客系统</div>
</body>
</html>