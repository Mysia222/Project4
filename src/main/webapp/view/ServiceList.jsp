<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 24.07.2016
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"  session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="style.css" type="text/css" media="screen" rel="stylesheet" />
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
                    <input type="submit" name="cabinet" value="${toTheCabinet}">
                    <input type="hidden" name="command" value="USER_CABINET">
                </form>
                <p class="wrongLogin">${serviceInUse}</p>
                <table>
                <tr><th>${name}</th><TH>${price}</TH><TH>${enableAction}</TH></tr>
                <c:forEach var="service" items="${services}" varStatus="status">
                 <tr>
                    <td>${service.name}</td>
                    <td>${service.price}</td>
                    <%--<td><input type="hidden" name="id" value="${service.id}"/></td>--%>
                    <td><c:if test="${idSet.contains(service.id)}">
                        <form action="/Controller" method="post">
                            <input type="submit" name="remove_user_service" value="${remove_service}">
                            <input type="hidden" name="command" value="USER_REMOVE_SERVICE">
                            <input type="hidden" name="id" value="${service.id}"/>
                        </form>
                        </c:if>
                        <c:if test="${!idSet.contains(service.id)}">
                        <form action="/Controller" method="post">
                            <input type="submit" name="set_user_service" value="${set_service}">
                            <input type="hidden" name="command" value="USER_SET_SERVICE">
                            <input type="hidden" name="id" value="${service.id}"/>
                        </form>
                    </c:if></td>

                 </tr>
                </c:forEach>
                </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
