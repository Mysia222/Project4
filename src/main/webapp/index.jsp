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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>tell</title>
</head>
<body>
<div>
    <div  class="wrapper ">
        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <form action="/Controller" method="post">
                        <br>
                        ${login}:
                        <p class="wrongLogin" >${wrongLogin}</p>
                        <input type="text" name="login">
                        <br>
                        ${password}:
                        <p class="wrongLogin" >${wrongPassword}</p>
                        <input type="password" name="password">
                        <br>
                        <input type="submit" name="submit_enter" value="${submit_enter}">
                        <input type="hidden" name="command" value="ENTER">
                    </form>
                    <form action="/Controller" method="post">
                        <input type="submit" name="create_account" value="${create_account}">
                        <input type="hidden" name="command" value="USER_CREATE">
                    </form>

                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
