<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/public.css">
</head>

<body>
<div class="header">Xiner Online Market</div>
<hr width="100%"/>
<%--display error msg--%>
<ul>
    <c:forEach var="errors" items="${errors}">
        <li class="errors">${errors}</li>
    </c:forEach>
</ul>

<form action="controller" method="post">
    <table width="100%" align="center">

        <tr height="40">
            <td width="50%" align="right"><img src="images/3.jpg" align="absmiddle"/>&nbsp;&nbsp; Account:</td>
            <td><input type="text" name="userid" class="textfield"/></td>
        </tr>
        <tr height="40">
            <td width="50%" align="right"><img src="images/2.jpg" align="absmiddle"/>&nbsp;Password:</td>
            <td><input type="password" name="password" class="textfield"/></td>
        </tr>
        <tr height="40">
            <td align="left">&nbsp;</td>
            <td><input align="left" type="image" src="images/login_button.jpg" onclick="document.forms[0].fn.value='login'"/>
                &nbsp;&nbsp; <a href="controller?action=reg_init"><img src="images/reg_button.jpg" border="0"/></a>
            </td>
        </tr>
    </table>
    <input type="hidden" name="action" value="login">
</form>
<%@include file="footer.jsp" %>
</body>
</html>