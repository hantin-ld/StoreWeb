<%-- 
    Document   : login
    Created on : Sep 6, 2022, 9:39:38 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center text-danger">Trang đăng nhập</h1>

<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        Tài khoản hoặc mật khẩu không đúng vui lòng đăng nhập lại. 
    </div>
</c:if>
<c:if test="${param.accessDenied != null}">
    <div class="alert alert-danger">
        Bạn không có quyền truy cập !!! 
    </div>
</c:if>

<c:url value="/login" var="action"/>

<form method="post" action="${action}">
    <div class="form-group">
        <label for="username">Username :</label>
        <input type="text" id="username" name="username" class="form-control"/>
        <!--trường name="" lưu giá trị parammeter trong SpringSecuirityConfig-->
    </div>
    <div class="form-group">
        <label for="password">Password :</label>
        <input type="password" id="password" name="password" class="form-control"/>
    </div>
    
    <div class="form-group " >
        <input type="submit" value="ĐĂNG NHẬP" class="btn btn-danger "/>
    </div>
    
</form>
