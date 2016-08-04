<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 24.07.2016
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"  session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/style.css" type="text/css" media="screen" rel="stylesheet" />
<html>
<head>
    <title>Service List</title>
</head>
<body>
<div>
    <div  class="wrapper ">
        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <form action="/Controller" method="post">
                        <input type="submit" value="${createService}">
                        <input type="hidden"  name="command" value="SERVICE_CREATE_NEW">
                    </form>
                    <form action="/Controller" method="post">
                        <input type="submit" name="cabinet" value="${toTheCabinet}">
                        <input type="hidden" name="command" value="SERVICE_ADMIN_CABINET">
                    </form>
                    <p class="wrongLogin"> ${nameInUse}</p>
                    <table>

                        <tr><th>${name}</th><th>${price}</th>
                            <th>${change}</th><th>${delete}</th></tr>
                        <c:forEach var="service" items="${services}" varStatus="status">
                            <c:if test="${!service.edit}">
                            <tr>
                                <td>${service.name}</td>
                                <td>${service.price}</td>
                                <td><form action="/Controller" method="post">
                                    <input type="submit" value ="${change}"/>
                                    <input type="hidden" name="command" value ="SERVICE_CHANGE_SERVICE"/>
                                    <input type="hidden" name="id" value="${service.id}"/>
                                </form></td>
                                <td><form action="/Controller" method="post">
                                    <input type="submit"  value ="${delete}"/>
                                    <input type="hidden" name="command" value="SERVICE_DELETE_SERVICE"/>
                                    <input type="hidden" name="id" value="${service.id}"/>
                                </form></td>
                            </tr></c:if>
                            <c:if test="${service.edit}">
                                <tr>
                                    <form action="/Controller" method="post">
                                    <td><input type="text" name="name" value="${service.name}"></td>
                                    <td><input type="number" min="0" name="price" value="${service.price}"></td>
                                    <td>
                                        <input type="submit"  value ="${save}"/>
                                        <input type="hidden" name="command" value="SERVICE_SAVE_SERVICE"/>
                                        <input type="hidden" name="id" value="${service.id}"/>
                                    </form></td>
                                    <td><form action="/Controller" method="post">
                                        <input type="submit" value ="${delete}"/>
                                        <input type="hidden" name="command" value ="SERVICE_DELETE_SERVICE"/>
                                        <input type="hidden" name="id" value="${service.id}"/>
                                    </form></td>
                                </tr></c:if>

                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
