<%-- 
    Document   : homePage
    Created on : Sep 13, 2022, 7:01:53 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center">Trang chủ</h1>

<!--phân trang-->
<ul class="pagination">
    <c:forEach var="i" begin="1" end="${Math.ceil(productCounter/18)}">
        <li class="page-item"><a class="page-link" href="<c:url value="/" />?page=${i}">${i}</a></li>
    </c:forEach>
</ul>

<div class="row">
    <c:forEach var="p" items="${products}">
        <div class="col-md-4 col-xs-12" style="padding-top: 16px">
            <div class="card">
                <a href="<c:url value="/products/${p.id}" />">   
                    <c:choose>
                        <c:when test="${p.image != null && p.image.startsWith('http')== true}">
                            <img class="card-img-top img-fluid" src="${p.image}" alt="Card image" />
                        </c:when>
                        <c:when test="${p.image == null || p.image.startsWith('http')== false }">
                            <img class="card-img-top img-fluid" src="<c:url value="/images/productDefault.png"/>" alt="Card image" />
                        </c:when>
                    </c:choose>
                </a>
                <div class="card-body">
                    <h4 class="card-title">${p.name}</h4>
                    <p class="card-text">${p.price} VND</p>
                    <a href="#" class="btn btn-primary ignore-click" onclick="addToCart(${p.id},'${p.name}',${p.price})">Đặt hàng</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<hr>
<div class="alert alert-success">
    <h3 class="text-center">CÁC SẢN PHẨM BÁN CHẠY HIỆN NAY:</h3>
</div>
<hr>
<div class="row">
    <c:forEach var="h" items="${hotProduct}">
        <div class="col-md-4 col-xs-12" style="padding-top: 16px">
            <div class="card">
                <a href="<c:url value="/products/${h[0]}" />">
                    <c:choose>
                        <c:when test="${h[3] != null && h[3].startsWith('http')== true}">
                            <img class="card-img-top img-fluid" src="${h[3]}" alt="Card image" />
                        </c:when>
                        <c:when test="${h[3] == null || h[3].startsWith('http')== false }">
                            <img class="card-img-top img-fluid" src="<c:url value="/images/productDefault.png"/>" alt="Card image" />
                        </c:when>
                    </c:choose>
                </a>
                <div class="card-body">
                    <h4 class="card-title">${h[1]}</h4>
                    <p class="card-text">${h[2]} VND</p>
                    <p class="text-danger">Số lượng bán: ${h[4]}</p>
                    <a href="#" class="btn btn-primary" onclick="addToCart(${h[0]},'${h[1]}',${h[2]})">Đặt hàng</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<hr>
<div class="alert alert-success">
    <h3 class="text-center">CÁC SẢN PHẨM ĐƯỢC BÌNH LUẬN NHIỀU NHẤT HIỆN NAY:</h3>
</div>
<hr>
<div class="row">
    <c:forEach var="hc" items="${hotCommentProduct}">
        <div class="col-md-4 col-xs-12" style="padding-top: 16px">
            <div class="card">
                <a href="<c:url value="/products/${hc[0]}" />">
                    <c:choose>
                        <c:when test="${hc[3] != null && hc[3].startsWith('http')== true}">
                            <img class="card-img-top img-fluid" src="${hc[3]}" alt="Card image" />
                        </c:when>
                        <c:when test="${hc[3] == null || hc[3].startsWith('http')== false }">
                            <img class="card-img-top img-fluid" src="<c:url value="/images/productDefault.png"/>" alt="Card image" />
                        </c:when>
                    </c:choose>
                </a>
                <div class="card-body">
                    <h4 class="card-title">${hc[1]}</h4>
                    <p class="card-text">${hc[2]} VND</p>
                    <p class="text-danger">Số lượng thảo luận: ${hc[4]}</p>
                    <a href="#" class="btn btn-primary" onclick="addToCart(${hc[0]},'${hc[1]}',${hc[2]})">Đặt hàng</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

<script>
        for (var i = 0; i < document.getElementsByClassName("ignore-click").length; i++) {
            document.getElementsByClassName("ignore-click")[i].addEventListener('click', function (event) {
                event.preventDefault();
                return false;
            });
        }
</script>