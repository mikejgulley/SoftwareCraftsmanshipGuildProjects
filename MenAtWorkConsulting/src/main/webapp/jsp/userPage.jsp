<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <title>${pageTitle}</title>

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
    </head>

    <body>
        <nav class="navbar navbar-inverse">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!--<a class="navbar-brand" href="${pageContext.request.contextPath}/home">Men at Work Consulting-->
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                        <img alt="Brand" src="${pageContext.request.contextPath}/img/menAtWork_primLogo_sm.png">
                    </a>
                    <a class="navbar-brand" id="brand-title" href="${pageContext.request.contextPath}/home">Men at Work Consulting</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav" id="navbar">
                        <li class="active"><a href="${pageContext.request.contextPath}/home">Home<span class="sr-only">(current)</span></a></li>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li class="dropdown">
                                <a href="${pageContext.request.contextPath}/admin" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Admin <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <!--<li><a href="${pageContext.request.contextPath}/admin">Admin Home</a></li>-->
                                    <li><a href="${pageContext.request.contextPath}/admin/addPost">Add Post</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/addPage">Add Page</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/readMessages">Read Messages</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/editUsers">Edit Users</a></li>
                                    <li><a href="${pageContext.request.contextPath}/admin/guestPosts">Review Guest Posts</a></li>
                                </ul>
                            </li>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_SUBADMIN')">
                            <li class="dropdown">
                                <a href="${pageContext.request.contextPath}/admin" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Admin <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="${pageContext.request.contextPath}/admin/addGuestPost">Add Guest Post</a></li>
                                </ul>
                            </li>
                            </sec:authorize>
                        <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
                    </ul>
                    <div class="nav navbar-nav navbar-right">
                        <sec:authorize access="isAuthenticated()">
                            <form class="navbar-form navbar-right login-out-buttons" action="${pageContext.request.contextPath}/j_spring_security_logout" method="GET">
                                <button type="submit" class="btn btn-primary">Log out</button>
                            </form>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <form class="navbar-form navbar-right login-out-buttons"action="${pageContext.request.contextPath}/login" method="GET">
                                <button type="submit" class="btn btn-primary">Login</button>
                            </form>

                            <form class="navbar-form navbar-right login-out-buttons"action="${pageContext.request.contextPath}/register" method="GET">
                                <button type="submit" class="btn btn-primary">Register</button>
                            </form>
                        </sec:authorize>

                    </div>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
        <div class="container">
            <div class="col-md-12">
                <!--<div class="row">-->
                <!--<div id="titleContainer">-->
                <h3 class="admin-task-title">${pageTitle}</h3>
                <!--</div>-->
                <!--<div id="postContainer">-->
                <div>
                    ${pageBody}
                </div>
                <!--</div>-->
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div class="row">
                        <div id="edit-delete-page-button-area">
                            <div class="col-xs-12" text-align="right">
                                <a class="edit-button" href="${pageContext.request.contextPath}/pages/editPage/${pageId}">Edit</a>
                                <a class="delete-button" id="deleteButton">Delete</a> 
                            </div>
                        </div>
                    </div>
                </sec:authorize>
                <hr>
            </div>
        </div>
    </div>
    <div class="container">
        <footer>
            <p hidden id="pageId">${pageId}</p>
            <div class="col-md-6" id="copyrightDiv">
                <p id="copyright">&copy;2015 | Men at Work Consulting</p>
            </div>
        </footer>
    </div> <!-- /container -->

    <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/navbar.js"></script>
    <script src="${pageContext.request.contextPath}/js/userPage.js"></script>
</body>
</html>