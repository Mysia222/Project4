<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 23.07.2016
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"  isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../style.css" type="text/css" media="screen" rel="stylesheet" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                       ${userInfo}:<br>
                        <table>
                            <tr>
                                <td>${fName}:</td>
                                <td><input type="text" name="fName"></td>
                            </tr>
                            <tr>
                                <td>${sName}:</td>
                                <td><input type="text" name="sName"></td>
                            </tr>
                            <tr>
                                <td>${login}:</td>
                                <td><input type="text" name="login"></td>
                            </tr>
                            <tr>
                                <td>${password}:</td>
                                <td><input type="text" name="password"></td>
                            </tr>
                        </table>
                           <input type="submit" name="create_account" value="${create}">
                           <input type="hidden" name="command" value="USER_CREATE">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
