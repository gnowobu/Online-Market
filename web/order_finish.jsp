<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Order Complete</title>
<link rel="stylesheet" type="text/css" href="css/public.css">
<style type="text/css">
a:link {
    font-size: 18px;
    color: #DB8400;
    text-decoration: none;
    font-weight: bolder;
}
a:visited {
    font-size: 18px;
    color: #DB8400;
    text-decoration: none;
    font-weight: bolder;
}
a:hover {
    font-size: 18px;
    color: #DB8400;
    text-decoration: underline;
    font-weight: bolder;
}
</style>
</head>

<body>
<div class="header">Xiner Online Market</div>
<hr width="100%" />
<div align="center" >
  <p class="text7"> Thanks for purchasing！ </p>
  <p class="text7"> Your order id：${orderid} </p>
  <p class="text7"> Enjoy! </p>
   <p class="text7">
       <a href="controller?action=main">Back to Main page</a>
  </p>
</div>
<%@include file="footer.jsp" %>

</body>
</html>
