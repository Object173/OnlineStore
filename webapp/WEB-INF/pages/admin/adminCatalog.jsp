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
<c:if test="${!empty listCategory}">
  <table class="table_price">
    <caption>
      <a href="<c:url value="/adminCatalog/${currentCategory.parent_id}"/>">${currentCategory.title}</a>
    </caption>
    <c:forEach items="${listCategory}" var="category">
      <c:if test="${category.parent_id==currentCategory.id && category.id!=currentCategory.id}">
        <tr>
          <td><a href="<c:url value="/adminCatalog/${category.id}"/>">${category.title}</a></td>
          <td><a href="<c:url value="/editCategory/${category.id}"/>">Редактировать</a></td>
          <td><a href="<c:url value="/removeCategory/${category.id}"/>">Удалить</a></td>
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
      <th width="80">Редактировать</th>
      <th width="80">Удалить</th>
    </tr>
    <c:forEach items="${listProduct}" var="product">
      <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.creator}</td>
        <td>${product.description}</td>
        <td>${product.cost}</td>
        <td>${product.count}</td>
        <td><a href="<c:url value="/editProduct/${product.id}"/>">Редактировать</a></td>
        <td><a href="<c:url value="/removeProduct/${product.id}"/>">Удалить</a></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<div>
  <div style="float: left;">
  <c:url var="addProductAction" value="/adminCatalog/addProduct"/>
  <form:form action="${addProductAction}" modelAttribute="product" float="right">
    <hl>Добавить товар</hl>
  <table>
    <c:if test="${!empty product.name}">
      <tr>
        <td>
          <form:label path="id">
            <spring:message text="Индекс"/>
          </form:label>
        </td>
        <td>
          <form:input path="id" readonly="true" size="8" disabled="true"/>
          <form:hidden path="id"/>
        </td>
      </tr>
    </c:if>
    <tr>
      <td>
        <form:label path="category_id">
          <spring:message text="Категория"/>
        </form:label>
      </td>
      <td>
        <form:select path="category_id">
          <c:forEach items="${listCategory}" var="cat">
            <form:option value="${cat.id}">${cat.title}</form:option>
          </c:forEach>
        </form:select>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="name">
          <spring:message text="Название"/>
        </form:label>
      </td>
      <td>
        <form:input path="name"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="creator">
          <spring:message text="Производитель"/>
        </form:label>
      </td>
      <td>
        <form:input path="creator"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="description">
          <spring:message text="Описание"/>
        </form:label>
      </td>
      <td>
        <form:textarea path="description"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="cost">
          <spring:message text="Цена"/>
        </form:label>
      </td>
      <td>
        <form:input path="cost"/>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="count">
          <spring:message text="Количество"/>
        </form:label>
      </td>
      <td>
        <form:input path="count"/>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <c:if test="${!empty product.name}">
          <input type="submit"
                 value="<spring:message text="Изменить товар"/>"/>
        </c:if>
        <c:if test="${empty product.name}">
          <input type="submit"
                 value="<spring:message text="Добавить товар"/>"/>
        </c:if>
      </td>
    </tr>
  </table>
  </form:form>
</div>
<div style="float: left;">
  <c:url var="addCategoryAction" value="/adminCatalog/addCategory"/>
  <form:form action="${addCategoryAction}" modelAttribute="category">
    <hl>Добавить категорию</hl>
  <table>
    <c:if test="${!empty category.title}">
      <tr>
        <td>
          <form:label path="id">
            <spring:message text="ID"/>
          </form:label>
        </td>
        <td>
          <form:input path="id" readonly="true" size="8" disabled="true"/>
          <form:hidden path="id"/>
        </td>
      </tr>
    </c:if>
    <tr>
      <td>
        <form:label path="parent_id">
          <spring:message text="Категория"/>
        </form:label>
      </td>
      <td>
        <form:select path="parent_id">
          <c:forEach items="${listCategory}" var="cat">
            <form:option value="${cat.id}">${cat.title}</form:option>
          </c:forEach>
        </form:select>
      </td>
    </tr>
    <tr>
      <td>
        <form:label path="title">
          <spring:message text="Заголовок"/>
        </form:label>
      </td>
      <td>
        <form:input path="title"/>
      </td>
    </tr>
    <tr>
      <td colspan="2">
        <c:if test="${!empty category.title}">
          <input type="submit"
                 value="<spring:message text="Изменить категорию"/>"/>
        </c:if>
        <c:if test="${empty category.title}">
          <input type="submit"
                 value="<spring:message text="Добавить категорию"/>"/>
        </c:if>
      </td>
    </tr>
  </table>
  </form:form>
  </div>
</div>
</html>
