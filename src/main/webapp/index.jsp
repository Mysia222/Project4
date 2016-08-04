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
                    <div class="flags">
                        <form class="flags" action="/Controller" method="post">
                            <input class="flags_UA" type="submit" value=""/>
                            <input type="hidden" name="command" value="LOCALE_INDEX" />
                            <input type="hidden" name="country" value="UA" />
                        </form>
                        <form  class="flags" action="/Controller" method="post">
                            <input class="flags_EN" type="submit" value=""/>
                            <input type="hidden" name="command" value="LOCALE_INDEX" />
                            <input type="hidden" name="country" value="EN" />
                        </form>
                    </div>

                    <form action="/Controller" method="post">
                        <br>
                        <p class="wrongLogin" >${wrongLogin}</p>
                        ${login}:
                        <input type="text" name="login" value="${preparedLogin}">
                        <br>
                        ${password}:
                        <input type="password" name="password">
                        <br>
                        <input type="submit" name="submit_enter" value="${submit_enter}">
                        <input type="hidden" name="command" value="USER_ENTER">
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
