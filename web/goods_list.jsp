<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Goods List</title>
</head>
<link rel="stylesheet" type="text/css" href="css/public.css">
<style type="text/css">
    table {
        border-collapse: collapse;
    }


    .col1 {
        padding-top: 5px;
        border-top: 1px dashed #666666;
        text-align: center;
    }


    .col2 {
        padding-top: 5px;
        border-top: 1px dashed #666666;
        text-align: center;
    }


    .col3 {
        padding-top: 5px;
        border-top: 1px dashed #666666;
        text-align: center;
    }
</style>
<body>
<jsp:include page="goods_header.jsp" flush="true" >
    <jsp:param name="image" value="list.jpg" />
</jsp:include>
<hr width="100%"/>
<div class="text3" align="center">Choose the products you like</div>
<br>
<table width="100%" border="0" align="center">
    <tr bgcolor="#b4c8ed">
        <th>Name</th>
        <th width="20%">Price</th>
        <th width="40%">Add to cart</th>
    </tr>
    <c:forEach var="goods" items="${goodsList}" varStatus="status">
        <tr
                <c:if test="${status.index % 2 == 0}">
                    bgcolor='#ffffff'
                </c:if>
                <c:if test="${status.index % 2 != 0}">
                    bgcolor='#edf8ff'
                </c:if>
        >
            <td class="col1"><a href="controller?action=detail&id=${goods.id}">${goods.description}</a></td>
            <td class="col2">￥${goods.price}</td>
            <td class="col3"><a href="controller?action=add&pagename=list&id=${goods.id}&name=${goods.name}&price=${goods.price}">Add to cart</a></td>
        </tr>
    </c:forEach>

</table>

<hr/>
<div align="center">
    <ul class="pagination">

        <li><a href="controller?action=paging&page=prev">«</a></li>
        <c:forEach var="page" begin="1" end="${totalPageNumber}">
            <li><a
                    <c:if test="${page == currentPage}">
                        class="active"
                    </c:if>
                    href="controller?action=paging&page=${page}">${page}</a></li>
        </c:forEach>
        <li><a href="controller?action=paging&page=next">»</a></li>
    </ul>
</div>

<%@include file="footer.jsp" %>

</body>
</html>
