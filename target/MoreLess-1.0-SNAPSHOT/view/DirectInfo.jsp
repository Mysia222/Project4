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
    <title>Edit Information</title>
</head>
<body>
<div>
    <div  class="wrapper ">

        <div class="mainbody ">
            <div class="aboutcontwrapper ">
                <div class="aboutcont ">
                    <%--${pageContext.servletContext.contextPath}--%>
                    <form action="/Controller" method="post">
                        <p class="wrongLogin"> ${loginInUse}</p>
                        ${userInfo}:<br>
                        <table>
                            <tr>
                                <td>${fNameH}:</td>
                                <td><input type="text" pattern="${patternName}" oninvalid="setCustomValidity('${namePatternErr}')" onchange="try{setCustomValidity('')}catch(e){}" name="fName" value="${savedFName}"></td>
                            </tr>
                            <tr>
                                <td>${sNameH}:</td>
                                <td><input type="text" pattern="${patternName}" oninvalid="setCustomValidity('${namePatternErr}')" onchange="try{setCustomValidity('')}catch(e){}" name="sName" value="${savedSName}"></td>
                            </tr>
                            <tr>
                                <td>${loginH}:</td>
                                <td><input type="text"  pattern="${patternLogin}" oninvalid="setCustomValidity('${logPatternErr}')" onchange="try{setCustomValidity('')}catch(e){}"  name="login" value="${savedLogin}"></td>
                            </tr>
                            <tr>
                                <td>${passwordH}:</td>
                                <td><input type="text" pattern="${patternPassword}" oninvalid="setCustomValidity('${passwordPatternErr}')" onchange="try{setCustomValidity('')}catch(e){}" name="password" value=""></td>
                            </tr>
                            </table>
                        <input type="submit" name ="direct_info" value="${save}">
                        <input type="hidden" name ="command" value="USER_DIRECT_INFO">


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
