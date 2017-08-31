<%--
  Created by IntelliJ IDEA.
  User: adMin
  Date: 11.08.2017
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adMin
  Date: 10.08.2017
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.discExchange.path.TakenDiscPath" %>
<html>
<head>
    <jsp:include page="common/_headPart.jsp"/>
    <title>Taken by me</title>
</head>
<body>
<jsp:include page="common/_menu.jsp"/>

<div class="container">

    <div class="panel panel-default">
        <div class="panel-heading">Discs taken by me</div>
        <div class="panel-body">
            <c:if test="${!empty takenDiscList}">
                <div class="table-responsive">
                    <table class="table table-striped table-condensed">
                        <tr>
                            <th> ID</th>
                            <th> Description</th>
                            <th> Disc name</th>
                            <th> Owner</th>
                            <th> Return</th>

                        </tr>
                        <c:forEach items="${takenDiscList}" var="takenDisc">
                            <tr>
                                <td>${takenDisc.id}</td>
                                <td>${takenDisc.description}</td>
                                <td>${takenDisc.disc.name}</td>
                                <td>${takenDisc.ownerUser.nickname}</td>
                                <td>
                                    <button class="btn btn-sm btn-info"
                                            onclick="location.href='<c:url value='${TakenDiscPath.Return}/${takenDisc.id}' />'"
                                    >Return</button>
                                    <%--<td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>
                                    <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>--%>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
            <c:if test="${empty takenDiscList}">
                <p class="bg-info">No discs found</p>
            </c:if>
        </div>
    </div>


</div>
<jsp:include page="common/_footer.jsp"/>
<jsp:include page="common/_scriptPart.jsp"/>
</body>
</html>
