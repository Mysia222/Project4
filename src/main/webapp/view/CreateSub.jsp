<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 23.07.2016
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<link href="../style.css" type="text/css" media="screen" rel="stylesheet" />
<html>
<head>
    <title>Create account</title>
</head>
<body>
<div>
    <div  class="wrapper ">
        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <%--${pageContext.servletContext.contextPath}--%>

                    <form action="/Controller" method="post">
                        ${loginInUse}
                       User Data:<br>
                        <table>
                            <tr>
                                <td>First Name:</td>
                                <td><input type="text" name="first_name"></td>
                            </tr>
                            <tr>
                                <td>Second Name:</td>
                                <td><input type="text" name="second_name"></td>
                            </tr>
                            <tr>
                                <td>Password:</td>
                                <td><input type="text" name="password"></td>
                            </tr>
                            <tr>
                                <td>Login:</td>
                                <td><input type="text" name="login"></td>
                            </tr>
                        </table>
                            <input type="submit" name="create" value="Create subscriber">
                            <input type="submit" name="test" value="${testSetAttribute}">
                            <input type="submit" name="test1" value="${testSetAttribute1}">
                            <input type="submit" name="test2" value="${testSetAttribute2}">
                            <input type="hidden" name="command" value="USER_CREATE">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
