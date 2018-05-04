<%--
  Created by IntelliJ IDEA.
  User: Friday
  Date: 09.12.2017
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Adding Basket</title>
</head>
<body>

<form action="AddBasketServlet">
    <div >
       Time Of Picking Up: <input type="date" name="new_basket_date" value="2013-01-08">
        <p> Color: <input type="text" name="new_basket_color"></p>
        <p> Spaciousness: <input type="number" value="0" name="new_basket_spaciousness"></p>
        <p> <input type="submit" name="add" value="Add"></p>
    </div>
</form>


</body>
</html>
