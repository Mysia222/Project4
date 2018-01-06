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
                    <form action="/Controller" method="post">
                        ${moneyValue}:
                        <input type="number" name="addToBalance"><br>
                        <input type="submit" name ="foot_the_bill" value="${payMoney}">
                        <input type="hidden" name="command" value="USER_FOOT_THE_BILL">
                    </form>
                        <form action="/Controller" method="post">
                            <input type="submit" name="cabinet" value="${toTheCabinet}">
                            <input type="hidden" name="command" value="USER_CABINET">
                        </form>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
