<%--
  Created by IntelliJ IDEA.
  User: Friday
  Date: 09.12.2017
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Infromation page</title>
</head>
<body>
<ul>
<c:forEach items="${baskets}" var="item">
    <li>
        <form action="UpdateBasketServlet"> <input   readonly name="basket_id" value="${item.id}"> time: <input type="date" name="basket_date" value="${item.timeOfPicingkUp}">  color:<input name="basket_color" value="${item.color}"> spaciousness: <input name="basket_spaciousness" value="${item.spaciousness}"> <input type="submit" name="update" value="Update"> <input type="submit" name="delete" value="delete"> <input type="submit" name="show_products" value="Products"></form>
    </li>
</c:forEach>
</ul>

<a href="add_basket.jsp">Add new basket</a>


</body>
</html>
