<%-- 
    Document   : leftAdmin
    Created on : Nov 9, 2022, 10:52:53 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar bg-light">
    <!-- Links -->
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/admin/category-statistic"/>">Thống kê sản phẩm theo danh mục</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/admin/product-statistic"/>">Thống kê doanh thu theo từng sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/admin/product-month-statistic"/>">Thống kê doanh thu theo thời gian</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-danger" href="<c:url value="/"/>">Quay lại trang chủ</a>
        </li>
    </ul>

</nav>
