<%@ include file="/taglibs.jsp"%>

<div class="future-laws sixteen columns">
  <h2>Майбутні законопроекти</h2>
      <table border="0">
        <tr>
        <th>№</th><th>назва</th><th>дата</th>
        </tr>
        <c:forEach var="law" items="${laws}" >
          <tr>
              <td>${law.id}</td><td><a href="${law.link}">${law.caption}</a></td><td>08.01.13</td>
          </tr>
        </c:forEach>
      </table>
</div>
