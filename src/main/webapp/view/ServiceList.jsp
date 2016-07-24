<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 24.07.2016
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"  session="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                        <tr>
                            <td>${service.name}</td>
                            <td>${service.price}</td>
                        </tr>
                        </c:forEach>
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
