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
                    <%--${pageContext.servletContext.contextPath}--%>
                    <%--<form action="./T1Servlet" method="get">--%>

                            <table>
                             <tr><th>Name</th><TH>Price</TH></tr>
                                <c:forEach var="service" items="${services}"   varStatus="status">
                                <%--<form action="/T1Servlet">--%>
                                        <tr>
                                             <td>${service.name}</td>
                                             <td>${service.price}</td>
                                            <%--<td><a href="/setService?id=${service.id}">Set</a> </td>--%>
                                             <td><input type="hidden" name="id" value="${service.id}"/></td>
                                             <td><c:if test="${idSet.contains(service.id)}">
                                                 <a href="/T1Servlet?command=REMOVE_SERVICE&id=${service.id}">Remove service</a></c:if>
                                             <c:if test="${!idSet.contains(service.id)}">
                                                 <a href="/T1Servlet?command=SET_SERVICE&id=${service.id}">Set service</a></c:if></td>
                                        </tr>
                                <%--</form>--%>
                                </c:forEach>
                                <%--<input type="submit" name="command" value="SET_SERVICE"/><br>--%>
                                <%--<input type="submit" name="command" value="REMOVE_SERVICE"/><br>--%>
                                <%--<a href="/T1Servlet?command=SAVE_NEW_INFO">Save info</a>--%>
                            </table>

                    <%--</form>--%>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
