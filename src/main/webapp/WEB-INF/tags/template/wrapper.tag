<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/twitter-bootstrap/yeti-bootstrap.min.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/font-awesome/font-awesome.min.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/normalize/normalize.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/site/main.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.11.1.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!--  Definicao do namespace da aplicacao  -->
<script type="text/javascript" >
Application = {
	name: "DaRuaSquad",
	context: "${pageContext.request.contextPath}",
	version: "0.0.1",
};
</script>

</head>
<body>
	<template:menutop></template:menutop>
	<div class="container">
		<!-- Inicio da view --> 
		<jsp:doBody />
		<!-- Final da view -->
	</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/twitter-bootstrap/bootstrap.min.js"></script>
</html>
