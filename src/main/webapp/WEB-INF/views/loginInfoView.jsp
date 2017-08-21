<%--
  Created by IntelliJ IDEA.
  User: adMin
  Date: 09.08.2017
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="common/_headPart.jsp"/>

    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Info page</title>

</head>
<body>

    <jsp:include page="common/_menu.jsp"/>

    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">Info</div>
            <div class="panel-body">
                <p class="bg-success">You are signed</p>
                <p>User Name: <strong>${userVm.name}</strong> </p>
            </div>
        </div>
        <%--<p>Gender: ${userVm.gender }</p>--%>
    </div>

    <jsp:include page="common/_footer.jsp"/>
    <jsp:include page="common/_scriptPart.jsp"/>
</body>
</html>
