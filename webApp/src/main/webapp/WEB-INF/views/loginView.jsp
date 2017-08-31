<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adMin
  Date: 10.08.2017
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.discExchange.path.UserPath" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="common/_headPart.jsp"/>


    <meta name="description" content="">
    <meta name="author" content="">


    <title>Login page</title>

    <%--<!-- Custom styles for this template -->
    <link href="<c:url value="/resources/viewLocal/navbar.css"/>" rel="stylesheet">--%>
</head>

<body>


<div class="container">

    <div class="row" style="margin-top:100px">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <div >
                <c:if test="${!empty errorString}">
                    <div class="alert alert-danger" role="alert">
                            ${errorString}
                    </div>
                </c:if>

                <form method="post" action="<c:url value="${UserPath.DoLogin}"/>">

                    <div class="form-group">
                        <label for="inputLogin">User Name</label>
                        <input type="text" class="form-control" id="inputLogin" placeholder="User Name" name="userName"
                               value="${userVm.name}"/>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Password</label>
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password"
                               name="password" value="${userVm.password}"/>
                    </div>
                    <%--
                     <div class="checkbox">
                        <label>
                            <input type="checkbox" name="rememberMe" value="Y">
                            Remember me
                        </label>
                    </div>
                     --%>
                    <button type="submit" class="btn btn-info">Sign in</button>
                </form>
            </div>
        </div>
    </div>


    </div>
</div>

<jsp:include page="common/_scriptPart.jsp"/>
</body>
</html>
