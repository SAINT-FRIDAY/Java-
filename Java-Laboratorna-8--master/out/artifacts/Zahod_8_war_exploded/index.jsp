<%--
  Created by IntelliJ IDEA.
  User: Friday
  Date: 07.12.2017
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.*" %>
<%@ page import="foo.Basket" %>
<%@ page import="java.util.List" %>
<%@ page import="foo.DataBaseMethods" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <style>
    .column-left{ float: left; width: 33%; }
    .column-right{ float: right; width: 33%; }
    .column-center{ display: inline-block; width: 33%; }
  </style>
  <body>

  <form action="MyServlet">
    <div class="column-left">
      <select id="operation_list" name="operation"  >
        <option  selected value="All Baskets">All Baskets</option>
      </select>
    <input type="submit" name="view" value="Send">
    </div>

  </form>
  </body>

</html>
