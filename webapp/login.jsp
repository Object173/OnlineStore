<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf8">
  <title><spring:message text="Login" /></title>
  <link href="${pageContext.request.contextPath}/resources/css/loginStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<a href="../../../index.jsp" id="adres">Вернуться к каталогу</a>
<br/>
<form id="loginForm" method="POST" action="<c:url value="/j_spring_security_check" />">
  <c:if test="${!empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}">
    <font color="red"> <spring:message text="Login error" />
      : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
  </c:if>
  <div class="field">
    <label>Логин:</label>
    <div class="input"><input type="text" name="j_username" id="login" /></div>
  </div>
  <div class="field">
    <label>Пароль:</label>
    <div class="input"><input type="password" name="j_password" id="pass" /></div>
  </div>
  <div class="submit">
      <button type="submit">Войти</button>
      <a href="<c:url value="/registration"/>">Регистрация</a>
  </div>
</form>
<br/>
</body>
</html>
