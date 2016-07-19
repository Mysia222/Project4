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
                        Contrtact:
                        <br>
                        ${contract}<br>
                        Balance:
                        <br>
                        ${balance}<br>
                        User Data:<br>
                        Name:
                        ${fName}<br>
                        Second Name:
                        ${sName}<br>
                        Password:
                        ${pass}<br>
                        Login:
                        ${log}<br>

                    </form>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
