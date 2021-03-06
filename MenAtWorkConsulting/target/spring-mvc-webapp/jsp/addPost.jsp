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

        <title>Admin | Men at Work Consulting</title>

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
        <script type="text/javascript">
            tinymce.init({
                selector: "textarea",
                theme: "modern",
                plugins: [
//                    "advlist autolink lists link image charmap print preview hr anchor pagebreak",
                    "advlist autolink lists link charmap print preview hr anchor pagebreak",
                    "searchreplace wordcount visualblocks visualchars code fullscreen",
                    "insertdatetime media nonbreaking save table contextmenu directionality",
                    "emoticons template paste textcolor colorpicker textpattern"
                ],
//                toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
                toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link",
                toolbar2: "print preview media | forecolor backcolor emoticons",
                image_advtab: true,
                templates: [
                    {title: 'Test template 1', content: 'Test 1'},
                    {title: 'Test template 2', content: 'Test 2'}
                ],
                relative_urls: false,
                entity_encoding: "numeric"
            });
        </script>
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
                        <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
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
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <h3 class="admin-task-title">Add Blog Post:</h3>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_SUBADMIN')">
                    <h3 class="admin-task-title">Submit Guest Blog Post:</h3>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <form method="POST" action="${pageContext.request.contextPath}/admin/addPost">
                        <div class="form-group">
                            <label for="postTitle">Title</label>
                            <input type="text" class="form-control" id="postTitle" placeholder="Post Title" autofocus="true">
                        </div>
                        <div class="form-group">
                            <label for="from">From <span class="start-finish-date-text">(0 = no starting date)</span></label>
                            <input type="text" class="form-control" id="from" name="from" placeholder="Starting Date">
                        </div>
                        <div class="form-group">
                            <label for="to">to <span class="start-finish-date-text">(0 = no expiration date)</span></label>
                            <input type="text" class="form-control" id="to" name="to" placeholder="Expiration Date">
                        </div>
                        <div class="form-group">
                            <textarea name="content" style="width:100%"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="tags">Tags <span class="start-finish-date-text">(separate each tag with a comma)</span></label>
                            <input type="text" class="form-control" id="tags" name="tags" placeholder="Tags">
                        </div>
                        <button class="btn btn-primary" type="submit" id="add-post-button">Add Post</button>
                    </form>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_SUBADMIN')">
                    <form method="POST" action="${pageContext.request.contextPath}/admin/submitGuestPost">
                        <div class="form-group">
                            <label for="postTitle">Title</label>
                            <input type="text" class="form-control" id="postTitle" placeholder="Post Title" autofocus="true">
                        </div>
                        <div class="form-group">
                            <label for="from">From <span class="start-finish-date-text">(0 = no starting date)</span></label>
                            <input type="text" class="form-control" id="from" name="from" placeholder="Starting Date">
                        </div>
                        <div class="form-group">
                            <label for="to">to <span class="start-finish-date-text">(0 = no expiration date)</span></label>
                            <input type="text" class="form-control" id="to" name="to" placeholder="Expiration Date">
                        </div>
                        <div class="form-group">
                            <textarea name="content" style="width:100%"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="tags">Tags <span class="start-finish-date-text">(separate each tag with a comma)</span></label>
                            <input type="text" class="form-control" id="tags" name="tags" placeholder="Tags">
                        </div>
                        <button class="btn btn-primary" type="submit" id="add-guest-post-button">Submit Guest Post for Approval</button>
                    </form>
                </sec:authorize>
            </div>
        </div>
        <div class="container">
            <hr>
            <footer>
                <div class="col-md-6">
                    <p id="copyright">&copy;2015 | Men at Work Consulting</p>
                </div>
                <!--                <div class="col-md-6">
                                    <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/login" method="GET">
                                        <button type="submit" class="btn btn-primary">Login</button>
                                    </form>
                                </div>-->
            </footer>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/navbar.js"></script>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <script src="${pageContext.request.contextPath}/js/addPost.js"></script>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_SUBADMIN')">
            <script src="${pageContext.request.contextPath}/js/guestPosts.js"></script>
        </sec:authorize>F
    </body>
</html>

