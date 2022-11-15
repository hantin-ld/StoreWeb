<%-- 
    Document   : header
    Created on : Sep 5, 2022, 2:49:55 PM
    Author     : Acer
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<nav class="navbar navbar-expand-md bg-dark navbar-dark ">
     <!--Brand--> 
    <a class="navbar-brand active" href="<c:url value="/" />">
        Store Web
    </a>

     <!--Toggler/collapsibe Button--> 
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

     <!--Navbar links--> 
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <c:forEach var="cate" items="${categories}">
                <li class="nav-item">
                    <c:url value="/" var="catePath">
                        <c:param name="CateId" value="${cate.id}"></c:param>
                    </c:url>
                    <a class="nav-link" href="${catePath}">${cate.name}</a>
                </li>
            </c:forEach>
            <li class="nav-item">
                <a class="nav-link" href="#">Liên hệ</a>
            </li>
            
            <!--đăng nhập đăng xuất đăng kí-->
            <%--<c:when test="${pageContext.request.userPrincipal.name == null}">--%>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/login" />">
                        <i class="fa fa-user"></i>
                        Đăng nhập
                    </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/register" />">
                        Đăng ký
                    </a>
                </li>
            </c:if>
            <%--</c:when>--%>
            <%--<c:when test="${pageContext.request.userPrincipal.name != null}">--%>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/" />">
                        <c:if test="${currentUser.avatar != null}">
                            <img src="${currentUser.avatar}" width="25px" height="auto" alt="img" class="img-fluid rounded-circle"/>
                        </c:if>
                        <c:if test="${currentUser.avatar == null}">
                            <img class="rounded-circle img-fluid"  src="<c:url value="/images/userDefault1.png" />" style="width: 25px" alt="img-d"/>
                        </c:if>    
                        ${pageContext.request.userPrincipal.name}
                    </a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/logout" />">
                        Đăng xuất
                    </a>
                </li>
            </c:if>
            <%--</c:when>--%>
            
            <li>
                <a href="<c:url value="/cart" />" class="nav-link">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <div class="badge badge-danger" id="cartCounter">${cartCounter}</div>
                </a>
            </li>
        </ul>
        <form action="<c:url value="/"/>" class="form-inline">
            <input class="form-control mr-sm-2" type="text" name="kw" placeholder="Nhập tên sản phẩm ...">
            <button class="btn btn-success" type="submit" value="Search">Search</button>
        </form>
    </div>   
</nav>
            
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="bg-dark">
        <div class="form-inline " style="margin: 0 16px; padding: 8px 0 ">
            <a href="#" class="btn btn-danger mr-sm-2" >QUẢN LÝ SẢN PHẨM</a>
            <a href="<c:url value="/admin/statistic"/>" class="btn btn-danger">THỐNG KÊ - BÁO CÁO</a>
        </div>
    </div>
    
        
</sec:authorize>             
