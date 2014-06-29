<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@taglib prefix="map" tagdir="/WEB-INF/tags/map" %>
<template:wrapper>
	<div class="searchContainer">
		<input type="text" id="search" class=" col-md-11 col-sm-11 col-xs-12"/>
		<button type="button" class="trigger btn btn-info btn-sm col-md-1 col-sm-1 col-xs-12"><i class="fa fa-search fa-1x"></i></button>
	</div>
    <map:fullmap></map:fullmap>
</template:wrapper>
