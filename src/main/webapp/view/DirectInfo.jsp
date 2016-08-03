<%--
  Created by IntelliJ IDEA.
  User: Славик
  Date: 23.07.2016
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
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
                    <form action="/Controller" method="post">
                        ${userInfo}:<br>
                        <table>
                            <tr>
                                <td>${fNameH}:</td>
                                <td><input type="text" name="fName"></td>
                            </tr>
                            <tr>
                                <td>${sNameH}:</td>
                                <td><input type="text" name="sName"></td>
                            </tr>
                            <tr>
                                <td>${loginH}:</td>
                                <td><input type="text" name="login"></td>
                            </tr>
                            <tr>
                                <td>${passwordH}:</td>
                                <td><input type="text" name="password"></td>
                            </tr>
                            </table>
                        <input type="submit" name ="direct_info" value="${save}">
                        <input type="hidden" name ="command" value="USER_DIRECT_INFO">


                    </form>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
