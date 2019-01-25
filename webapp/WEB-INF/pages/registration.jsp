<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Registration</title>
    <link href="/resources/css/loginStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="../../../index.jsp" id="adres">Вернуться к каталогу</a>
<br/>
<c:url var="addUserAction" value="/registrationUser"/>
<form:form id="loginForm" action="${addUserAction}" modelAttribute="user">
  <c:if test="${!empty error}">
    <hl>${error}</hl>
  </c:if>
  <div class="field">
        <form:label path="username">Имя пользователя</form:label>
        <div class="input"><form:input path="username" id="login"/></div>
  </div>
  <div class="field">
        <form:label path="password">Пароль</form:label>
        <div class="input"><form:input type="password" path="password" id="pass"/></div>
  </div>
  <div class="field">
        <form:label path="email">email</form:label>
          <div class="input"><form:input type="email" path="email" id="email"/></div>
  </div>
  <div class="field">
        <form:label path="phone">Телефон</form:label>
          <div class="input"><form:input type="tel" path="phone" id="phone"/></div>
  </div>
  <div class="submit">
        <button type="submit">Регистрация</button>
  </div>
</form:form>
</body>
</html>
