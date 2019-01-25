<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
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
      <a href="<c:url value="/catalog"/>">Каталог</a></li>
  </ul>
</nav>
<c:if test="${!empty itemList}">
  <table class="table_price">
    <caption>
      <label>Сумма: ${sumCost} </label>
      <a href='<c:url value="/createOrder"/>'>Оформить</a>
    </caption>
    <tr>
      <th width="80">Индекс</th>
      <th width="80">Артикул</th>
      <th width="120">Name</th>
      <th width="200">Description</th>
      <th width="100">Cost</th>
      <th width="100">Count</th>
      <th width="100">sum</th>
      <th width="80">Remove</th>
    </tr>
    <c:forEach items="${itemList}" var="item">
      <tr>
        <td>${item.id}</td>
        <td>${item.articul}</td>
        <td>${item.name}</td>
        <td>${item.description}</td>
        <td>${item.cost}</td>
        <td>${item.count}</td>
        <td>${item.sum}</td>
        <td><a href="<c:url value="/removeCart/${item.id}"/>">Удалить</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<c:if test="${!empty orderList}">
<table class="table_price">
  <caption>Заказы</caption>
  <tr>
    <th width="80">ID</th>
    <th width="120">status</th>
    <th width="120">cost</th>
    <th width="80">Info</th>
    <th width="80">Cancel</th>
  </tr>
  <c:forEach items="${orderList}" var="order">
  <tr>
    <td>${order.id}</td>
    <td>${order.status}</td>
    <td>${order.cost}</td>
    <td><a href="<c:url value="/orderInfo/${order.id}"/>">Инфо</a></td>
    <td><a href="<c:url value="/orderCancel/${order.id}"/>">Отмена</a></td>
  </tr>
  </c:forEach>
</table>
</c:if>
</body>
</html>
