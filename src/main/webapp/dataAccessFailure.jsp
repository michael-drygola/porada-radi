<%@ include file="/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<h3>Data Access Failure</h3>
<p>
    <c:out value="${requestScope.exception.message}"/>
</p>

<!--
<%
Exception ex = (Exception) request.getAttribute("exception");
ex.printStackTrace(new java.io.PrintWriter(out));
%>
-->

<a href="<c:url value='/'/>">&#171; Home</a>
