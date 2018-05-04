<%--
  Created by IntelliJ IDEA.
  User: Friday
  Date: 10.12.2017
  Time: 0:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<p>Products:</p>

<ul>
    <c:forEach items="${products}" var="product">
        <li>
            <form action="UpdateProductServlet">  <input type="hidden"  readonly name="product_id" value="${product.id}"> name:<input name="product_name" value="${product.name}"> time: <input type="date" name="product_date" value="${product.dateOfCreation}">  ShelfTime:<input name="product_shelftime" value="${product.shelfTimes}">   <input type="hidden" readonly name="product_origin" value="${product.origin_id}"> Price:<input name="product_price" value="${product.price}"> <input type="submit" name="update" value="Update"> <input type="submit" name="delete" value="delete"> </form>
        </li>
    </c:forEach>
</ul>
<form action="UpdateProductServlet"><input type="hidden" readonly name="product_origin" value="${id}"><input type="submit" name="add_product" value="Add New Product"></form>

<a href="index.jsp">ToMain</a>
</body>
</html>
