<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">

<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" media="screen" />
<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet" media="screen" />
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" media="screen" />
<link href="<c:url value="/resources/css/blue/style.css" />" rel="stylesheet" media="screen" />
<link href="<c:url value="/resources/css/jquery-ui.css" />" rel="stylesheet" media="screen" />
<link href="<c:url value="/resources/css/jquery-ui.structure.css" />" rel="stylesheet" media="screen" />
<link href="<c:url value="/resources/css/jquery-ui.theme.css" />" rel="stylesheet" media="screen" />

<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.cookie.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.tablesorter.min.js" />"></script>
<script src="<c:url value="/resources/js/dashboard.js" />"></script>
<script src="<c:url value="/resources/js/main.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>

</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<spring:message code='label.lang.select' var="selectLang" />		
		
		<div id="language-picker" title="${ selectLang }"
				data-toggle="popover"
				data-content=""
				data-placement="bottom"></div>
		<div id="language-picker-content">
			<form id="language-picker-form" action="#" method="GET">
				<input name="id" type="hidden" value="${ computer.id }"/>
				<input id="lang" name="lang" type="hidden" />
				<div class="language-selection" onclick="changeLanguage('fr')">
					<div id="select-fr" class="select-lang">
						<spring:message code='label.lang.fr' />
					</div>
				</div>
				<div class="language-selection" onclick="changeLanguage('en')">
					<div id="select-en" class="select-lang">
						<spring:message code="label.lang.en" />
					</div>
				</div>
			</form>
		</div>
		
		<div class="container">
			<a class="navbar-brand title" href="dashboard">
				<spring:message code="label.title.header" />
			</a>
		</div>
		
		<c:choose>
			<c:when test="${ pageContext.request.userPrincipal.name != null }">
				<form action="logout" method="POST">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="btn btn-danger btn-log">
						<div class="glyphicon glyphicon-log-out"></div>
						&nbsp;<spring:message code="label.button.logout" />
					</button>
				</form>
			</c:when>
		<c:otherwise>
			<form action="login" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button type="submit" class="btn btn-success btn-log">
					<div class="glyphicon glyphicon-log-in"></div>
					&nbsp;<spring:message code="label.button.login" />
				</button>
			</form>
		</c:otherwise>
		</c:choose>
	</header>