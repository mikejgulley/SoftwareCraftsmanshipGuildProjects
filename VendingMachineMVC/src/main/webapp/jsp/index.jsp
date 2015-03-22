<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary" id="title-panel">
                        <div class="panel-heading">
                            <h1 class="panel-title" id="page-title">Myko-G's Vending Machine: <span id="sub-title">Get your nom on!</span></h1>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            Inventory:
                        </div>
                        <div class="panel-body" id="inventory-bg">
                            <div class="col-md-12">
                                <table class="table" id="inventory-table">
                                    <tbody id="contentRows">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3" id="column2">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Current Balance:</div>
                                <div class="panel-body" id="current-balance"></div>
                                <div class="panel-body" id="buy-button-panel-body">
                                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addFundsModal">Add Funds</button>
                                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#makeChangeModal">Make Change</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="button-set">
                        <div class="col-md-12">
                            <div class="panel panel-primary">
                                <div class="panel-heading">Make Selection:</div>
                                <div class="panel-body">
                                    <div class="panel-body text-center">
                                        <label class="radio-inline">
                                            <input type="radio" name="menuChoices" id="inlineRadio1" value="A1"> A1
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="menuChoices" id="inlineRadio2" value="A2"> A2
                                        </label>
                                    </div>
                                    <div class="panel-body text-center">
                                        <label class="radio-inline">
                                            <input type="radio" name="menuChoices" id="inlineRadio3" value="B1"> B1
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="menuChoices" id="inlineRadio4" value="B2"> B2
                                        </label>
                                    </div>
                                    <div class="panel-body text-center">
                                        <label class="radio-inline">
                                            <input type="radio" name="menuChoices" id="inlineRadio5" value="C1"> C1
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="menuChoices" id="inlineRadio6" value="C2"> C2
                                        </label>
                                    </div>
                                    <div class="panel-body text-center">
                                        <label class="radio-inline">
                                            <input type="radio" name="menuChoices" id="inlineRadio7" value="D1"> D1
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="menuChoices" id="inlineRadio8" value="D2"> D2
                                        </label>
                                    </div>
                                    <div class="panel-body text-center">
                                        <!--<button type="button" class="btn btn-success" id="buy-button" data-toggle="modal" data-target="#buyItemModal">Buy Item</button>-->
                                        <button type="button" class="btn btn-success" id="buy-button" data-toggle="modal" data-target="#buyItemModal">Buy Item</button>
                                    </div>
                                    <!--</form>-->
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <footer class="text-center">
                <hr id="footer-hr"/>
                <div class="panel panel-default" id="footer-panel">
                    <p>&copy; 2015 - <a href="https://www.linkedin.com/in/mikejgulley" target="_blank">Michael J. Gulley</a></p>
                    <p>Powered by <a href="https://www.java.com" target="_blank">Java</a> and <a href="http://www.getbootstrap.com" target="_blank">Bootstrap</a></p>
                </div>
            </footer>
        </div>
        <%@include file="addFundsMakeChangeModalsFragment.jsp" %>
        <%@include file="buyItemModalFragment.jsp" %>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/vending-machine.js"></script>
    </body>
</html>

