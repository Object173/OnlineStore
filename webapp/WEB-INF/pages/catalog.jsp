<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
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
      <a href="<c:url value="/cartPage"/>">Корзина</a>
    </li>
    <c:if test="${empty userName}">
      <li>
        <a href="../../login.jsp">Авторизация</a>
      </li>
    </c:if>
    <c:if test="${!empty userName}">
      <li>
      <a href="<c:url value="/userPage"/>">${userName}</a>
      </li>
      <li>
        <a href="<c:url value="/j_spring_security_logout"/>">Выход</a>
      </li>
    </c:if>
  </ul>
</nav>
<c:if test="${!empty listCategory}">
  <table class="table_price">
    <caption>
      <a href="<c:url value="/catalog/${currentCategory.parent_id}"/>">${currentCategory.title}</a>
    </caption>
    <c:forEach items="${listCategory}" var="category">
      <c:if test="${category.parent_id==currentCategory.id && category.id!=currentCategory.id}">
        <tr>
          <td><a href="<c:url value="/catalog/${category.id}"/>">${category.title}</a></td>
        </tr>
      </c:if>
    </c:forEach>
  </table>
</c:if>
<c:if test="${!empty listProduct}">
<table class="table_price">
  <tr>
    <th width="80">Индекс</th>
    <th width="120">Название</th>
    <th width="120">Производитель</th>
    <th width="200">Описание</th>
    <th width="100">Цена</th>
    <th width="100">Количество</th>
  </tr>
  <c:forEach items="${listProduct}" var="product">
  <tr>
    <form action="/addItemToBasket" method="post">
      <td>
        ${product.id}
        <input type="text" name="productId" value="${product.id}" hidden="false"/>
      </td>
      <td>${product.name}</td>
      <td>${product.creator}</td>
      <td>${product.description}</td>
      <td>
        ${product.cost}
          <input type="text" name="productCost" value="${product.cost}" hidden="false"/>
      </td>
      <td>${product.count}</td>
      <td><input type="number" step="1" min="1" max="${product.count}" name="productCount" value="1"/></td>
      <td><button type="submit">Купить</button></td>
    </form>
  </tr>
  </c:forEach>
</table>
  </c:if>
</body>
</html>
