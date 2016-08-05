<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 16.07.2016
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../style.css" type="text/css" media="screen" rel="stylesheet" />

<html>
<head>
    <title>Home</title>
</head>
<body>

<div>
    <div  class="wrapper ">

        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <%--${pageContext.servletContext.contextPath}--%>
                    <div class="flags">

                        <form class="flags" action="/Controller" method="post">
                            <input class="flags_UA" type="submit" value=""/>
                            <input type="hidden" name="command" value="LOCALE" />
                            <input type="hidden" name="country" value="UA" />
                        </form>
                        <form  class="flags" action="/Controller" method="post">
                            <input class="flags_EN" type="submit" value=""/>
                            <input type="hidden" name="command" value="LOCALE" />
                            <input type="hidden" name="country" value="EN" />
                        </form>
                    </div>
                        <%--<a href="/cabinet">Cabinet</a> <a href="/T1Servlet?command=DIRECT_USER_INFO">Direct info</a>--%>
                        <a href="/view/secure/Admin.jsp">Go test jsp</a>
                         <form action="/Controller" method="get">
                             <input type="submit" name="direct_service" value="${direct_service_button}">
                             <input type="hidden" name="command" value="USER_DIRECT_SERVICE">
                         </form>
                        <form action="/Controller" method="get">
                            <input type="submit" name="foot_the_bill" value="${foot_the_bill_button}">
                            <input type="hidden" name="command" value="USER_FOOT_THE_BILL">
                        </form>
                        <form action="/Controller" method="get">
                            <input type="submit" name="direct_info" value="${direct_info}">
                            <input type="hidden" name="command" value="USER_DIRECT_INFO">
                        </form>
                        <form action="/Controller" method="post">
                            <input type="submit" name="logout" value="${logout}">
                            <input type="hidden" name="command" value="LOGOUT">
                        </form>
                        <%--<a href="/T1Servlet?command=LOGOUT">Logout</a><br>--%>
                        ${blocked}
                        <table border="1px solid black">
                            <tr>
                                <td>${contractH}:</td>
                                <td>${contract}</td>
                            </tr>
                            <tr>
                                <td>${balanceH}</td>
                                <td>${balance}</td>
                            </tr>
                            <tr>
                                <td>${current_serviceH}:</td>
                                <td>
                                    <c:forEach items="${current_service}" var="item">
                                        ${item.name}<br>
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td>${fNameH}:</td>
                                <td>${fName}</td>
                            </tr>
                            <tr>
                                <td>${sNameH}:</td>
                                <td>${sName}</td>
                            </tr>
                            <tr>
                                <td>${loginH}:</td>
                                <td>${login}</td>
                            </tr>
                            <tr>
                                <td>${passwordH}:</td>
                                <td>${password}</td>
                            </tr>
                        </table>


                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
