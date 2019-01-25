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
    <li>
      <a href="../../index.jsp"><i class="fa fa-home fa-fw"></i>
        Вернуться в меню</a>
    </li>
    <li>
      <a href="<c:url value="/adminCatalog"/>"> Каталог </a>
    </li>
    <li>
      <a href="<c:url value="/adminUsers"/>"> Пользователи </a>
    </li>
    <li>
      <a href="<c:url value="/adminOrders"/>"> Заказы </a>
    </li>
    <c:if test="${!empty userName}">
      <li>
        <a href="<c:url value="/j_spring_security_logout"/>">Выход</a>
      </li>
    </c:if>
  </ul>
</nav>
<br/>
<c:if test="${!empty items}">
  <table class="table_price">
    <caption>
      <form action="/setOrderStatus" method="post">
        <label>Номер заказа: ${order.id} </label><br/>
        <input type="text" name="orderId" value="${order.id}" hidden="false"/>
        <label>Покупатель: ${order.username}</label><br/>
        <label>Статус: </label>
        <select name="status">
          <option value="canceled" <c:if test="${order.status.equals('canceled')}">selected</c:if>>отменен</option>
          <option value="processing" <c:if test="${order.status.equals('processing')}">selected</c:if>>оформлен</option>
          <option value="working" <c:if test="${order.status.equals('working')}">selected</c:if>>обрабатывается</option>
          <option value="completed" <c:if test="${order.status.equals('completed')}">selected</c:if>>завершен</option>
        </select>
        <input type="submit" value="set"/><br/>
        <label>Сумма заказа: ${order.cost} </label>
      </form>
    </caption>
    <tr>
      <th width="80">ID</th>
      <th width="80">Articul</th>
      <th width="120">Название</th>
      <th width="200">Описание</th>
      <th width="100">Цена</th>
      <th width="100">Количество</th>
      <th width="100">Сумма</th>
      <th width="80">Удалить</th>
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
        <td><a href="<c:url value="/adminOrderItemRemove/${item.id}"/>">Удалить</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</body>
</html>
