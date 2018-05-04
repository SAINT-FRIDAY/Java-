<%--
  Created by IntelliJ IDEA.
  User: Friday
  Date: 10.12.2017
  Time: 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
<p>Insert Data of a new Product</p>
<form action="AddProductServlet">

        <p>Basket id: <input  readonly type="number" name="res" value="${origin_id}"></p>
        <p> Name: <input type="text" name="new_product_name"></p>
       <p> Date Of Creation: <input type="date" name="new_product_date" value="2013-01-08"></p>
        <p> Price: <input type="number" value="0" name="new_product_price" step="0.05"></p>
        <p> ShelfTime: <input type="number" value="0" name="new_product_shelftime"></p>
        <input type="submit" name="add_product" value="Add New Product">
    </div>
</form>
</body>
</html>
