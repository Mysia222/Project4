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

                    <form action="./cabinet" method="get">
                        <a href="/cabinet">Cabinet</a><br>
                        ${blocked}
                        <table>
                            <tr>
                                <td>Contrtact:</td>
                                <td>${contract}</td>
                            </tr>
                            <tr>
                                <td>Balance</td>
                                <td>${balance}</td>
                            </tr>
                            <tr>
                                <td>Current service:</td>
                                <td>${service}</td>
                            </tr>
                            <tr>
                                <td>Name:</td>
                                <td>${fName}</td>
                            </tr>
                            <tr>
                                <td>Second Name:</td>
                                <td>${sName}</td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td>${pass}</td>
                            </tr>
                            <tr>
                                <td>Login:</td>
                                <td>${log}</td>
                            </tr>
                        </table>
                        <a href="/T1Servlet?command=DIRECT_USER_INFO">Direct info</a><br>
                        <a href="/T1Servlet?command=ORDER_SERVICE">Order service</a><br>
                        <a href="/T1Servlet?command=FOOT_THE_BILL">Foot the bill</a>
                    </form>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
