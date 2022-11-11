<%-- 
    Document   : productMonthStatis
    Created on : Nov 10, 2022, 3:49:46 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-danger">Thống kê doanh thu theo tháng</h1>

<form action="">
    <div class="form-group">
        <label>Tìm kiếm sản phẩm: </label>
        <input type="text" name="kw" class="form-control"/>
    </div> 
    <div class="form-group">
        <label>Từ thời điểm:</label>
        <input type="date" name="fromDate" class="form-control"/>
    </div> 
    <div class="form-group">
        <label>Đến thời điểm:</label>
        <input type="date" name="toDate" class="form-control"/>
    </div>
    <input type="submit" value="Báo Cáo" class="btn btn-danger" />
</form>

<div>
  <canvas id="myProductMonthStatisticChart"></canvas>
</div>


<table class="table">
    <tr>
        <th>Tháng</th>
        <th>Doanh thu</th>
    </tr>
    <c:forEach items="${prodMonthStatistic}" var="p">
        <tr>
            <td>${p[0]}/${p[1]}</td>
            <td>${p[2]} VND</td>
        </tr>
    </c:forEach>
</table>

<script>
    let prodLabels=[], prodInfors=[];
    
    <c:forEach items="${prodMonthStatistic}" var="p">
        prodLabels.push(`${p[0]}/${p[1]}`)
        prodInfors.push(${p[2]})
    </c:forEach>
    
    window.onload = function(){
        prodMonthChart("myProductMonthStatisticChart", prodLabels, prodInfors)
    }
</script>
