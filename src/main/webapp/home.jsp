<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 16.07.2016
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="style.css" type="text/css" media="screen" rel="stylesheet" />
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

                    <form action="/cabinet" method="get">
                        <a href="/cabinet">Cabinet</a> <a href="/T1Servlet?command=DIRECT_USER_INFO">Direct info</a>
                        <a href="/T1Servlet?command=ORDER_SERVICE">Order service</a>
                        <a href="/T1Servlet?command=FOOT_THE_BILL">Foot the bill</a><br>
                        ${blocked}
                        <table border="1px solid black">
                            <tr>
                                <td>Contract:</td>
                                <td>${contract}</td>
                            </tr>
                            <tr>
                                <td>Balance</td>
                                <td>${balance}</td>
                            </tr>
                            <tr>
                                <td>Current service:</td>
                                <td>${current_service}</td>
                            </tr>
                            <tr>
                                <td>Name:</td>
                                <td>${first_name}</td>
                            </tr>
                            <tr>
                                <td>Second Name:</td>
                                <td>${second_name}</td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td>${password}</td>
                            </tr>
                            <tr>
                                <td>Login:</td>
                                <td>${login}</td>
                            </tr>
                        </table>

                    </form>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
