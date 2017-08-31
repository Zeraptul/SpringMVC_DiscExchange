<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adMin
  Date: 10.08.2017
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.discExchange.path.DiscPath" %>
<html>
<head>
    <jsp:include page="common/_headPart.jsp"/>
    <title>Add disc</title>
</head>
<body>
    <jsp:include page="common/_menu.jsp"/>
    <div class="container">

        <div class="panel panel-default">
            <div class="panel-heading">Add disc</div>
            <div class="panel-body">
                <c:if test="${!empty errorString}">
                    <div class="alert alert-danger" role="alert">
                            ${errorString}
                    </div>
                </c:if>
                <c:if test="${!empty successString}">
                    <div class="alert alert-success" role="alert">
                            ${sucessString}
                    </div>
                </c:if>

                <form method="post" action="<c:url value="${DiscPath.DoAdd}"/>" >
                    <div class="row">
                        <div class="form-group col-sm-4">
                            <label for="inputLogin">Disc name</label>
                            <input type="text" class="form-control" id="inputLogin" placeholder="Disc name" name="discName" value= "${discVm.name}" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-sm-4">
                            <label for="exampleInputPassword1">Author name</label>
                            <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Author" name="authorName" value= "${discVm.author}" />
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-info">Add</button>
                    </div>
                </form>
            </div>
        </div>

    </div>
    <jsp:include page="common/_footer.jsp"/>
    <jsp:include page="common/_scriptPart.jsp"/>
</body>
</html>
