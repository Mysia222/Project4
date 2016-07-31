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
    <title>Users List</title>
</head>
<body>
<div>
    <div  class="wrapper ">

        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <%--${pageContext.servletContext.contextPath}--%>
                    <%--<form action="./T1Servlet" method="get">--%>
                    <a href="/secure/Controller?command=show+subs" >Subs List</a>
                    <a href="/cabinet" >Return to the cabinet</a>
                    <table border="1px">
                        <tr>
                            <th>Contract</th>
                            <TH>Balance</TH>
                            <TH>Current service</TH>
                            <TH>Login</TH>
                            <TH>Password</TH>
                            <TH>First Name</TH>
                            <TH>Second Name</TH>
                            <TH>Enable actions</TH>

                        </tr>
                        <c:forEach var="service" items="${users}"   varStatus="status">
                            <form action="/T1Servlet">
                                <tr>
                                     <td>${service.contract}</td>
                                     <td>${service.balance}</td>
                                     <td>${service.currentService}</td>
                                     <td>${service.info.login}</td>
                                     <td>${service.info.password}</td>
                                     <td>${service.info.firstName}</td>
                                     <td>${service.info.secondName}</td>
                                    <td><c:if test="${service.balance<0 && !service.blocked}"><a href="/T1Servlet?command=BLOCK_USER&id=${service.contract}">Block this subscriber</a></c:if>
                                    <c:if test="${service.balance>0 && service.blocked}"><a href="/T1Servlet?command=UNLOCK_USER&id=${service.contract}">Unlock this subscriber</a></c:if></td>
                                </tr>
                            </form>
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
