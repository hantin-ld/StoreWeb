<%-- 
    Document   : userDetail
    Created on : Nov 15, 2022, 8:24:10 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-danger mt-3">THÔNG TIN CÁ NHÂN</h1>

<div class="row mb-5" >
    <div class="col-md-4 col-xs-12">
        <c:choose>
            <c:when test="${currentUser.avatar != null && currentUser.avatar.startsWith('http')== true}">
                <img class="card-img-top img-fluid" src="${currentUser.avatar}" alt="user image" />
            </c:when>
            <c:when test="${currentUser.avatar == null || currentUser.avatar.startsWith('http')== false }">
                <img class="card-img-top img-fluid" src="<c:url value="/images/userDefault1.png"/>" alt="user image" />
            </c:when>
        </c:choose>
    </div>
    <div class="col-md-8 col-xs-12">
        <form class="form-inline blockquote">
            <label for="fullName" class="mr-2">Họ và tên: </label>
            <span id="fullName" >${currentUser.lastName} ${currentUser.firstName}</span>
        </form>
        <hr>
        <form class="form-inline blockquote">
            <label for="acc" class="mr-2">Tên tài khoản: </label>
            <span id="acc">${currentUser.username}</span>
        </form>
        <hr>
        <form class="form-inline blockquote">
            <label for="email" class="mr-2">Email: </label>
            <span id="email">${currentUser.email}</span>
        </form>
        <hr>
        <form class="form-inline blockquote">
            <label for="phone" class="mr-2">Số điện thoại: </label>
            <span id="phone">${currentUser.phone}</span>
        </form>
        <hr>
        <form class="form-inline blockquote">
            <label for="address" class="mr-2">Địa chỉ:</label>
            <span id="address">${currentUser.address}</span>
        </form>
        <hr>
        
        <div class="form-inline">
            <div class="mr-5">
                <a class="btn btn-danger" href="<c:url value="/"/>" >Quay lại</a>
            </div>
            <div class="mr-5">
                <a class="btn btn-danger" href="#" >Cập nhật</a>
            </div>
            
        </div>
    </div>
</div>
      
        