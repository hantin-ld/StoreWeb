<%-- 
    Document   : product-detail
    Created on : Sep 18, 2022, 7:51:38 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<hr>
<div class="row">
    <div class="col-md-5">
        <%--<c:if test="${product.image != null && product.image.startsWith('http')== true}">--%>
            <!--<img class="card-img-top img-fluid" src="${product.image}" alt="${product.name}" />-->
        <%--</c:if>--%>
        <%--<c:if test="${product.image == null || product.image.startsWith('http')== false}">--%>
            <!--<img class="card-img-top img-fluid" src="<c:url value="/images/productDefault.png"/>" alt="${product.name}" />-->
        <%--</c:if>--%>    
            
        <c:choose>
            <c:when test="${product.image != null && product.image.startsWith('http')== true}">
                <img class="card-img-top img-fluid" src="${product.image}" alt="Card image" />
            </c:when>
            <c:when test="${product.image == null || product.image.startsWith('http')== false }">
                <img class="card-img-top img-fluid" src="<c:url value="/images/productDefault.png"/>" alt="Card image" />
            </c:when>
        </c:choose>
    </div>
    <div class="col-md-7">
        <h2 class="text-primary">${product.name}</h2>
        <h3 class="text-danger">${product.price} VND</h3>
        <p>Mô tả: ${product.description}</p>
        <div>
            <input type="button" value="Đặt hàng" class="btn btn-danger" />
        </div>
    </div>
</div>
 
<form >
    <div class="form-group">
        <textarea class="form-control" id="commentId" placeholder="Nhập bình luận của bạn ..." rows="4"></textarea>
        <br>
        <input type="submit" onclick="addComment(${product.id})" value="Gửi bình luận" class="btn btn-danger"/>
    </div>
</form>        
        
<!--tùy theo đối tượng người dùng // chỉ dùng tạm thời // lưu ý như vầy rất chậm-->   
<div id="commentArea">
    <c:forEach items="${comments}" var="cm">
        <div class="row">
            <div class="col-md-2" style="padding: 5px">
                <c:if test="${cm.user.avatar == null}">
                    <img class="rounded-circle img-fluid"  src="<c:url value="/images/userDefault1.png" />" alt="error"/>
                </c:if>
                <c:if test="${cm.user.avatar != null}">
                    <img class="rounded-circle img-fluid"  src="${cm.user.avatar}" alt="img"/>
                </c:if>    
                
            </div>
            <div class="col-md-10 my-date">
                <p>${cm.content}</p>
                <i>${cm.createdDate}</i>
            </div>
        </div>
    </c:forEach>  
    
    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${Math.ceil(commentCounter/7)}">
            <c:url value="/products/${product.id}" var="path">
                <c:param name="page" value="${i}"></c:param>
            </c:url>
            <li class="page-item">
                <a class="page-link" href="${path}">${i}</a>
            </li>
        </c:forEach>
    </ul>
</div>
      

<script>
    window.onload = function(){
        let dates = document.querySelectorAll(".my-date > i");  
        for (let i = 0; i< dates.length; i++){
            let d = dates[i];
            d.innerText = moment(d.innerText).fromNow();
        }
    };
</script>        