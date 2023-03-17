<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista produktów</title>
<link rel="stylesheet" type="text/css" href="styl.css">
</head>
<body>
<h1>Lista produktów</h1>

<jsp:useBean id="productsBean" class="sklep.beans.ProductsBean"/>

<c:forEach var="product" items="${productsBean.allProducts}">
	<div class="product">
		<h3>${product.productName}</h3>
	    <div class="price">${product.price}</div>
    	<p>${product.description}</p>
	</div>
</c:forEach>

</body>
</html>
