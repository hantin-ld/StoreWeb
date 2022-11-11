<%-- 
    Document   : register
    Created on : Sep 6, 2022, 9:39:56 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h1 class="text-center text-danger">TRANG ĐĂNG KÝ</h1>

<c:url value="/register" var="action"/>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">${errMsg}</div>
</c:if>

<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <div class="form-group">
        <label for="name">Họ và tên lót: </label>
        <form:input type="text" id="name" path="lastName" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="name">Tên: </label>
        <form:input type="text" id="name" path="firstName" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="email">Email: </label>
        <form:input type="email" id="email" path="email" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="phone">Số điện thoại: </label>
        <form:input type="phone" id="phone" path="phone" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="username">Tài khoản: </label>
        <form:input type="text" id="username" path="username" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="password">Mật khẩu: </label>
        <form:input type="password" id="password" path="password" class="form-control"/>
    </div>
    <div class="form-group">
        <label for="confirm-password">Xác nhận mật khẩu: </label>
        <form:input type="password" id="confirm-password" path="confirmPassword" class="form-control"/>
    </div>
    
    <div class="form-group">
        <label for="avatar">Avatar (Hình đại diện): </label>
        <form:input type="file" id="file" path="file" class="form-control"/>
    </div>
    <!--<div class="form-group">-->
        <!--<label for="posit">Chức vụ người dùng: </label>-->
        <%--<form:select id="posit" path="position" cssClass="form-control">--%>
            <%--<c:forEach items="${positions}" var="ps" >--%>
                <!--<option value="${ps.id}">${ps.name}</option>-->
            <%--</c:forEach>--%>
        <%--</form:select>--%>
    <!--</div>-->
    <div class="form-group">
        <input type="submit" value="ĐĂNG KÝ" class="btn btn-danger "/>
    </div>   
</form:form>