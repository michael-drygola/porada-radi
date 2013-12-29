<%@ include file="/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<div class="future-laws sixteen columns">
  <h2>Майбутні законопроекти</h2>
      <table border="0">
        <tr>
        <th>№</th><th>назва</th><th>дата</th>
        </tr>
        <c:forEach var="law" items="${laws}" >
          <tr>
              <td>${law.id}</td><td><a href="${ctx}/details?law=${law.id}">${law.caption}</a></td><td>${law.formattedDate}</td>
          </tr>
        </c:forEach>
      </table>
</div>
