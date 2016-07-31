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
                    <a href="/secure/CreateService">Create new</a>
                    <table>
                        <tr><th>Name</th><TH>Price</TH></tr>
                        <c:forEach var="service" items="${services}"   varStatus="status">
                            <form action="/T1Servlet">
                                <tr>
                                    <td>${service.name}</td>
                                    <td>${service.price}</td>
                                    <td><input type="submit" name="command" value ="DELETE_SERVICE"/></td>
                                    <td><input type="submit" name="command" value="SERVICE_CHANGE"/></td>
                                    <%--<td><c:if test="${service.balance<0 && !service.blocked}"><a href="/T1Servlet?command=BLOCK_USER&id=${service.contract}">Block this subscriber</a></c:if>--%>
                                        <%--<c:if test="${service.balance>0 && service.blocked}"><a href="/T1Servlet?command=UNLOCK_USER&id=${service.contract}">Unlock this subscriber</a></c:if></td>--%>
                                    <td><input type="hidden" name="id" value="${service.id}"/></td>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
