<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adMin
  Date: 10.08.2017
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="common/_headPart.jsp"/>
    <title>My taken discs</title>
</head>
<body>
    <jsp:include page="common/_menu.jsp"/>

    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">My taken discs</div>
            <div class="panel-body">
                <c:if test="${!empty takenDiscList}">
                    <div class="table-responsive">
                        <table class="table table-striped table-condensed">
                            <tr>
                                <th> ID</th>
                                <th> Description </th>
                                <th> Disc name</th>
                                <th> Owner</th>
                                <th> Taker</th>

                            </tr>
                            <c:forEach items="${takenDiscList}" var="takenDisc">
                                <tr>
                                    <td>${takenDisc.id}</td>
                                    <td>${takenDisc.description}</td>
                                    <td>${takenDisc.disc.name}</td>
                                    <td>${takenDisc.ownerUser.nickname}</td>
                                    <td>
                                        <!--https://stackoverflow.com/questions/5935892/if-else-within-jsp-or-jstl-->
                                            <%--    ${!empty takendDisc.takerUser ? "some text when true" : "some text when false"}--%>
                                        <c:choose>
                                            <c:when test="${!empty takenDisc.takerUser}">
                                                ${takenDisc.takerUser.nickname}
                                            </c:when>
                                            <c:otherwise>
                                                null
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
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
