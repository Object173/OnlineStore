<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyShop</title>
  <link href="${pageContext.request.contextPath}/resources/css/catalogStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<table class="table_price">
  <caption>Интернет магазин</caption>
      <tr>
        <td></td>
        <td><a href="<c:url value="/catalog"/>">Каталог</a></td>
        <td><a href="<c:url value="/adminCatalog"/>">Администрирование</a></td>
      </tr>
</table>
</body>
</html>
