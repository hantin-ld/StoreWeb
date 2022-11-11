<%-- 
    Document   : categoryStatis
    Created on : Nov 9, 2022, 11:01:58 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-danger">Thống kê sản phẩm theo danh mục</h1>

<div>
  <canvas id="myCategoryStatisticChart"></canvas>
</div>

<table class="table">
    <tr>
        <th>Mã danh mục</th>
        <th>Tên danh mục</th>
        <th>Số lượng sản phẩm</th>
    </tr>
    <c:forEach items="${cateStatis}" var="i">
        <tr>
            <td>${i[0]}</td>
            <td>${i[1]}</td>
            <td>${i[2]}</td>
        </tr>
    </c:forEach>
</table>

<script>
    let cateLabels=[], cateinfors=[];
    
    <c:forEach items="${cateStatis}" var="i">
        cateLabels.push(`${i[1]}`)
        cateinfors.push(${i[2]})
    </c:forEach>
    
    window.onload = function(){
        cateChart("myCategoryStatisticChart", cateLabels, cateinfors)
    }
</script>
    