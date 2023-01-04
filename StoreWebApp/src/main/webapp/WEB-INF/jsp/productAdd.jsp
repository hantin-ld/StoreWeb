<%-- 
    Document   : product-add
    Created on : Jan 4, 2023, 7:32:02 PM
    Author     : Acer
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/admin/add-product" var="action" />
<h1 class="text-center text-danger">Thêm sản phẩm</h1>

<form:form method="post" action="${action}" modelAttribute="product" enctype="multipart/form-data">
    <div class="form-group">
        <label for="name">Tên sản phẩm:</label>
        <form:input type="text" id="name" path="name" cssClass="form-control"/>
    </div>
    
    <div class="form-group">
        <label for="price">Giá:</label>
        <form:input type="text" id="price" path="price" cssClass="form-control"/>
    </div>
    
    <div class="form-group">
        <label for="description">Mô tả: </label>
        <form:textarea id="description" path="description" cssClass="form-control" rows="3" />
    </div>    

    <div class="form-group">
        <label for="cate">Danh mục: </label>
        <form:select id="cate" path="category" cssClass="form-control">
            <c:forEach items="${categories}" var="cat">
                <option value="${cat.id}">${cat.name}</option>
            </c:forEach>
        </form:select>
    </div>
    
    <div class="form-group">
        <label for="file">Ảnh sản phẩm:</label>
        <form:input type="file" id="file" path="file" cssClass="form-control"/>
         <!--trường path="" là trường khai báo transient trong medicine.class (pojo)--> 
    </div>
        
    <div class="form-group">
         <input type="submit" value="Thêm sản phẩm" class="btn btn-danger"/>
    </div>    
</form:form>