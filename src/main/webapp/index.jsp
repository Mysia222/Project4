<%--
  Created by IntelliJ IDEA.
  User: potaychuk
  Date: 11.07.2016
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="style.css" type="text/css" media="screen" rel="stylesheet" />
<html>
<head>
    <title>tell</title>
</head>
<body>
<div>
    <div  class="wrapper ">
        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <form action="./home2" method="get">
                        <br>
                        login:
                        <p class="wrongLogin" >${wrongLogin}</p>
                        <input type="text" name="login">
                        <br>
                        password:
                        <p class="wrongLogin" >${wrongPassword}</p>
                        <input type="password" name="password">
                        <br>
                        <input type="submit" value="Enter"><br>
                        <a href="/view/CreateSub.jsp">Create account</a>
                    </form>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
