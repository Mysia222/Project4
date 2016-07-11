<%--
  Created by IntelliJ IDEA.
  User: potaychuk
  Date: 11.07.2016
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index.jsp</title>
</head>
<body>
<form action="./s" method="get">
    MoreLess game
    <br>
    ${textA}<br>
    ${textB}
    <br>
    <input type="text" name="number">
    <br>
    <input type="submit" value="Try">
</form>
${log}
</body>
</html>
