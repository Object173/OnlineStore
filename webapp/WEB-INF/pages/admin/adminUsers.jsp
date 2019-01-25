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
<c:if test="${!empty listUser}">
  <table class="table_price">
    <caption>Пользователи</caption>
    <tr>
      <th width="80">Индекс</th>
      <th width="200">Имя пользователя</th>
      <th width="200">Пароль</th>
      <th width="200">Доступ</th>
      <th width="200">Email</th>
      <th width="200">Телефон</th>
      <th width="80">Редактировать</th>
      <th width="80">Удалить</th>
    </tr>
    <c:forEach items="${listUser}" var="user">
      <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.password}</td>
        <td>${user.authority}</td>
        <td>${user.email}</td>
        <td>${user.phone}</td>
        <td><a href="<c:url value="/editUser/${user.id}"/>">Редактировать</a></td>
        <td><a href="<c:url value="/removeUser/${user.id}"/>">Удалить</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
</br>
<hl>Добавить пользователя</hl>
<c:url var="addUserAction" value="/adminUsers/addUser"/>
<form:form action="${addUserAction}" modelAttribute="user">
  <table>
    <c:if test="${!empty user.username}">
      <tr>
        <td>
          <form:label path="id">
            <spring:message text="Индекс"/>
          </form:label>
        </td>
        <td>
          <form:input path="id" readonly="true" disabled="true"/>
          <form:hidden path="id"/>
        </td>
      </tr>
    </c:if>
    <tr>
      <td>
        <form:label path="username">
          <spring:message text="Имя пользователя"/>
        </form:label>
      </td>
      <td>
        <form:input path="username"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="password">
          <spring:message text="Пароль"/>
        </form:label>
      </td>
      <td>
        <form:input path="password"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="authority">
          <spring:message text="Доступ"/>
        </form:label>
      </td>
      <td>
        <form:select path="authority">
          <form:option value="ROLE_USER">ROLE_USER</form:option>
          <form:option value="ROLE_ADMIN">ROLE_ADMIN</form:option>
        </form:select>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="email">
          <spring:message text="Email"/>
        </form:label>
      </td>
      <td>
        <form:input type="email" path="email"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="phone">
          <spring:message text="Телефон"/>
        </form:label>
      </td>
      <td>
        <form:input type="tel" path="phone"/>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <c:if test="${!empty user.username}">
          <input type="submit"
                 value="<spring:message text="Изменить пользователя"/>"/>
        </c:if>
        <c:if test="${empty user.username}">
          <input type="submit"
                 value="<spring:message text="Добавить пользователя"/>"/>
        </c:if>
      </td>
    </tr>
  </table>
</form:form>
</body>
</html>
