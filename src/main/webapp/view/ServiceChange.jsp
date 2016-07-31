<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 31.07.2016
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="../style.css" type="text/css" media="screen" rel="stylesheet" />
<html>
<head>
    <title>Create Service</title>
</head>
<body>
<div>
    <div  class="wrapper ">
        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <%--${pageContext.servletContext.contextPath}--%>
                    <a href="/cabinet" >Return to the cabinet</a>
                    <form action="/secure/UpdateService" method="get">
                        Service:<br>
                        <table>
                            <tr>
                                <td>Name:</td>
                                <td><input type="text" name="name"></td>
                            </tr>
                            <tr>
                                <td>Price:</td>
                                <td><input type="number" name="price"></td>
                            </tr>
                        </table>
                        <input type="hidden" name ="id" value="${id}">
                        <input type="submit" value="update">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
