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
    <title>User list</title>
</head>
<body>
<div>
    <div  class="wrapper ">

        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <form action="/Controller" method="post">
                        <input type="submit" value="${refresh}">
                        <input type="hidden" name="command" value="SERVICE_SUBSCRIBERS">
                    </form>
                    <form action="/Controller" method="post">
                        <input type="submit" name="cabinet" value="${toTheCabinet}">
                        <input type="hidden" name="command" value="SERVICE_ADMIN_CABINET">
                    </form>
                    <table >
                        <tr>
                            <th>${contractH}</th>
                            <TH>${balanceH}</TH>
                            <TH>${current_serviceH}</TH>
                            <TH>${loginH}</TH>
                            <TH>${fNameH}</TH>
                            <TH>${sNameH}</TH>
                            <TH>${enableAction}</TH>

                        </tr>
                        <c:forEach var="sub" items="${users}">

                                <tr>
                                     <td>${sub.contract}</td>
                                     <td>${sub.balance}</td>
                                     <td>
                                         <c:forEach items="${sub.currentService}" var="item">
                                         ${item.name}<br>
                                        </c:forEach>
                                    </td>
                                     <td>${sub.info.login}</td>
                                     <td>${sub.info.firstName}</td>
                                     <td>${sub.info.secondName}</td>
                                    <td><c:if test="${sub.balance<0 && !sub.blocked}"><form action="/Controller" method="post">
                                        <input type="submit"  value="${blockSubscriber}">
                                        <input type="hidden" name="id" value="${sub.contract}">
                                        <input type="hidden" name="command" value="SERVICE_SUBSCRIBER_BLOCK">
                                    </form></c:if>
                                    <c:if test="${sub.balance>=0 && sub.blocked}"><form action="/Controller" method="post">
                                        <input type="submit"  value="${unlockSubscriber}">
                                        <input type="hidden" name="id" value="${sub.contract}">
                                        <input type="hidden" name="command" value="SERVICE_SUBSCRIBER_UNLOCK">
                                    </form></c:if>
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
