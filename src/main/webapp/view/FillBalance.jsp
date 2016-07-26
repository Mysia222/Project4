<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 24.07.2016
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="style.css" type="text/css" media="screen" rel="stylesheet" />
<html>
<head>
    <title>Foot The Bill</title>
</head>
<body>
<div>
    <div  class="wrapper ">

        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <%--${pageContext.servletContext.contextPath}--%>
                    <form action="./T1Servlet" method="get">
                        PAY!:
                        <input type="number" name="addBalance"><br>
                        <%--<input type="hidden" name ="login" value=${login}>--%>
                        <%--<input type="hidden" name ="password" value=${password}>--%>
                        <input type="submit" name ="command" value="FILL_BALANCE">
                        <%--<a href="/T1Servlet?command=SAVE_NEW_INFO">Save info</a>--%>


                    </form>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
