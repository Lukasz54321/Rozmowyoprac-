<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="sklep.db.DBException"%>
<%@page import="java.util.List"%>
<%@page import="sklep.model.Product"%>
<%@page import="sklep.db.DBConnection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista produktów</title>
<link rel="stylesheet" type="text/css" href="styl.css">
</head>
<body>
<h1>Lista produktów</h1>

<%-- Miksowanie kodu HTML/JSP z kodem Javy - aż w takim stopniu robić tego nie należy. Gubimy strukturę kodu! --%>

<%
try(DBConnection db = DBConnection.open()) {
	List<Product> products = db.productDAO().readAll();
	for(Product product : products) {
%>
	<div class="product">
		<h3><%= product.getProductName() %></h3>
		<div class="price"><%= product.getPrice() %></div>
		<p><%= product.getDescription()%></p>
	</div>
		
<%	
	}
} catch (DBException e) {

%>
	<div class="error">
	<p><strong><%= e %> </strong></p>
	</div>
<%
}
%>

</body>
</html>
