<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Movie Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1 class="text-center">Myko-G's Movie Library</h1>
            <h1 class="text-center"><small><em>Negligibly the most snazzy of all libraries...</em></small></h1>
            <hr/>
            <%@include file="navBarFragment.jsp" %>
            <!--<h2>Home Page</h2>-->
            <%@include file="summaryTableFragment.jsp" %>
            <hr/>
        </div>
        <div class="footer">
            <footer class="container text-center">
                <p>&copy; 2015 - <a href="https://www.linkedin.com/in/mikejgulley" target="_blank">Michael J. Gulley</a></p>
                <p>Powered by <a href="https://www.java.com" target="_blank">Java</a> and <a href="http://www.getbootstrap.com" target="_blank">Bootstrap</a></p>
            </footer>
        </div>
        <%@include file="detailsEditModalFragment.jsp" %>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/movie-library.js"></script>
    </body>
</html>

