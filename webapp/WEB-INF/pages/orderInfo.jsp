<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
  <link href="/resources/css/menuStyle.css" rel="stylesheet" type="text/css">
  <link href="/resources/css/catalogStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav class="three">
  <ul>
    <li><i class="fa fa-home fa-fw"></i>
      <a href="<c:url value="/cartPage"/>">Вернуться к корзине</a>
  </ul>
</nav>
<br/>
<c:if test="${!empty items}">
  <table class="table_price">
    <caption>
      <label>Order ID: ${order.id} </label></br>
      <label>Order status: ${order.status} </label></br>
      <label>Order cost: ${order.cost} </label>
    </caption>
    <tr>
      <th width="80">Индекс</th>
      <th width="80">Артикул</th>
      <th width="120">Название</th>
      <th width="200">Описание</th>
      <th width="100">Цена</th>
      <th width="100">Количество</th>
      <th width="100">Сумма</th>
    </tr>
    <c:forEach items="${items}" var="item">
      <tr>
        <td>${item.id}</td>
        <td>${item.articul}</td>
        <td>${item.name}</td>
        <td>${item.description}</td>
        <td>${item.cost}</td>
        <td>${item.count}</td>
        <td>${item.sum}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</body>
</html>
