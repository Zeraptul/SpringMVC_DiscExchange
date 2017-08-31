<%--@elvariable id="userVm" type="com.discExchange.vm.UserVm"--%>
<%--@elvariable id="menu" type="com.discExchange.vm.Menu"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: adMin
  Date: 10.08.2017
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<!--https://stackoverflow.com/questions/3655316/browser-cant-access-find-relative-resources-like-css-images-and-links-when-cal/3658735#3658735-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.discExchange.path.UserPath" %>
<%@ page import="com.discExchange.path.DiscPath" %>
<%@ page import="com.discExchange.path.TakenDiscPath" %>

<!-- Custom styles for this template -->
<link href="<c:url value="/resources/viewLocal/navbar.css"/>" rel="stylesheet">
<div class="container">

    <!-- Static navbar -->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href=<c:url value="${UserPath.Info}"/> >Disc Exchange</a> <!--/user/loginInfo-->
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <%--<li class="${menu.allDiscs}"> <a href="<c:url value="${DiscPath.ListAll}"/>">Discs</a></li>--%>
                    <%--<li class="${menu.takenDiscAll}" > <a href="<c:url value="${TakenDiscPath.ListAll}"/>">Taken Discs</a></li>--%>
                    <li class="${menu.addDisc}"><a href="<c:url value="${DiscPath.Add}"/>">Add Disc</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="${menu.takenDiscAllAvailable}"><a href="<c:url value="${TakenDiscPath.AllAvailable}"/>">Available Discs</a></li>
                    <li class="${menu.takenDiscAllUserTaken}"><a href="<c:url value="${TakenDiscPath.AllUserTaken}"/>">Discs Taken by Me</a></li>
                    <li class="${menu.takenDiscAllTakenFromUser}"><a href="<c:url value="${TakenDiscPath.AllTakenFromUser}"/>">My Taken Discs </a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                <li>
                       <div style="line-height: 20px; padding: 15px 0"> Welcome back, ${userVm.name}</div>
                        </li>
                       <li>
                           <a href="<c:url value="${UserPath.Logout}"/>">Logout</a>
                       </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
</div> <!-- /container -->

