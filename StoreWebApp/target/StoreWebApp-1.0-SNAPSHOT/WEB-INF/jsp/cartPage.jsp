<%-- 
    Document   : cartPage
    Created on : Oct 1, 2022, 12:52:10 PM
    Author     : Acer
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-danger">gio hang</h1>

<c:if test="${carts == null}">
    <h4>Không có sản phẩm nào trong giỏ hàng</h4>
</c:if> 
    
<c:if test="${carts != null}">
    <table class="table">
        <tr id="product${c.productId}">
            <th>Mã sản phẩm:</th>
            <th>Tên sản phẩm:</th>
            <th>Đơn giá:</th>
            <th>Số lượng:</th>
            <th></th>
        </tr>
        <c:forEach items="${carts}" var="c">
            <tr>
                <td>${c.productId}</td>
                <td>${c.productName}</td>
                <td>${c.price} VND</td>
                <td>
                    <div class="form-group">
                        <input type="number" 
                               onblur="updateQuatityCart(this, ${c.productId})" 
                               value="${c.quantity}" 
                               class="form-control"/>
                    </div>
                </td>
                <td>
                    <input type="button" 
                           onclick="deleteFromCart(${c.productId})"
                           value="Delete" 
                           class="btn btn-danger" />
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <div>
        <h4 class="text-infor"> Tổng hóa đơn: <span id="cartAmount">${cartSumStatus.amount} VND</span></h4>
    </div>        
            
    <br>
    <input type="button" onclick="pay()" value="Thanh Toán" class="btn btn-danger" />
   
</c:if>
    <br><br>
   
