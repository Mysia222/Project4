<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 23.07.2016
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="style.css" type="text/css" media="screen" rel="stylesheet" />
<html>
<head>
    <title>DirectInfo</title>
</head>
<body>
<div>
    <div  class="wrapper ">

        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <%--${pageContext.servletContext.contextPath}--%>
                    <form action="./T1Servlet" method="get">

                        New User Data:<br>
                        Name:
                        <input type="text" name="first_name"><br>
                        Second Name:
                        <input type="text" name="second_name"><br>
                        Password:
                        <input type="text" name="password"><br>
                        Login:
                        <input type="text" name="login"><br>
                        <input type="submit" name ="command" value="SAVE_NEW_INFO">
                        <%--<a href="/T1Servlet?command=SAVE_NEW_INFO">Save info</a>--%>


                    </form>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
