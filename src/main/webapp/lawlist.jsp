<%@ include file="/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<h1>Laws list</h1>
<c:forEach var="law" items="${laws}" >
	<p>${law.caption} <a href="${law.link}">click</a></p>
</c:forEach>
