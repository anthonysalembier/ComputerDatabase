<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<html>
<head>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/css/font-awesome.css" />" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>
    
    <div class="not-found"></div>

    <section id="main">
        <div class="container">
            <div class="alert alert-danger">
                Error 404: Page not found. Too bad bitch!
                <br/>
                <!-- stacktrace -->
            </div>
        </div>
    </section>

    <script src="<c:url value="/resources/js/jquery.min.js" />"></script>
    <script src="<c:url value="/resources/css/bootstrap.min.css" />"></script>
    <script src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>
</html>