<%@ include file="/taglibs.jsp"%>

<h1>Laws list</h1>
<c:forEach var="law" items="${laws}" >
	<p>${law.caption} <a href="${law.link}">click</a></p>
</c:forEach>
