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
<c:if test="${!empty orderList}">
  <table class="table_price">
    <caption>Заказы</caption>
    <tr>
      <th width="80">Индекс</th>
      <th width="120">Покупатель</th>
      <th width="120">Статус</th>
      <th width="120">Цена</th>
      <th width="80">Редактировать</th>
      <th width="80">Удалить</th>
    </tr>
    <c:forEach items="${orderList}" var="order">
      <tr>
        <td>${order.id}</td>
        <td>${order.username}</td>
        <td>${order.status}</td>
        <td>${order.cost}</td>
        <td><a href="<c:url value="/adminOrderEdit/${order.id}"/>">Редактировать</a></td>
        <td><a href="<c:url value="/adminOrderRemove/${order.id}"/>">Удалить</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</body>
</html>
