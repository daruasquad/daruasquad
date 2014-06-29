<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@taglib prefix="midia" tagdir="/WEB-INF/tags/midia"%>

<template:wrapper>
	<midia:pre-galery-struct></midia:pre-galery-struct>

	<ul class="items--small">
		<li class="item"><a href="#"><img
				src="${pageContext.request.contextPath}/assets/img/s-galery/small-1.png"
				alt="" /></a></li>
		<li class="item"><a href="#"><img
				src="${pageContext.request.contextPath}/assets/img/s-galery/small-2.png"
				alt="" /></a></li>
		<li class="item"><a href="#"><img
				src="${pageContext.request.contextPath}/assets/img/s-galery/small-3.png"
				alt="" /></a></li>
		<li class="item"><a href="#"><img
				src="${pageContext.request.contextPath}/assets/img/s-galery/small-4.png"
				alt="" /></a></li>
	</ul>
	<ul class="items--big">
		<li class="item--big"><a href="#">
				<figure>
					<img
						src="${pageContext.request.contextPath}/assets/img/s-galery/big-1.jpg"
						alt="" />
					<figcaption class="img-caption">Caption</figcaption>
				</figure>
		</a></li>
		<li class="item--big"><a href="#">
				<figure>
					<img
						src="${pageContext.request.contextPath}/assets/img/s-galery/big-2.jpg"
						alt="" />
					<figcaption class="img-caption">Caption</figcaption>
				</figure>
		</a></li>
		<li class="item--big"><a href="#">
				<figure>
					<img
						src="${pageContext.request.contextPath}/assets/img/s-galery/big-3.jpg"
						alt="" />
					<figcaption class="img-caption">Caption</figcaption>
				</figure>
		</a></li>
		<li class="item--big"><a href="#">
				<figure>
					<img
						src="${pageContext.request.contextPath}/assets/img/s-galery/big-4.jpg"
						alt="" />
					<figcaption class="img-caption">Caption</figcaption>
				</figure>
		</a></li>
	</ul>
	<midia:pos-galery-struct></midia:pos-galery-struct>
</template:wrapper>
