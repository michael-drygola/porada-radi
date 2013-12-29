<%@ include file="/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<div class="future-laws sixteen columns">
  <h2>законопроект#${law.id}</h2>
  <h3>${law.caption}</h3>
  <a href="${law.link}">На сайті Верховної Ради</a>
  <p>${law.description}</p>
  <c:choose>
      <c:when test="${uservoted}">
        <h3>Результати голосування:</h3>
        <table border="1">
          <tr>
            <th>За</th>
            <th>Утримались</th>
            <th>Проти</th>
          </tr>
          <tr>
            <td>${for}</td>
            <td>${abstain}</td>
            <td>${against}</td>
          </tr>
        </table>
      </c:when>
      <c:otherwise>
         <form action="" method="post">
           <input type="hidden" name="law" value="${law.id}" />
           <button name="vote" value="1">ЗА</button>
           <button name="vote" value="0">УТРИМАВСЯ</button>
           <button name="vote" value="2">ПРОТИ</button>
         </form>
      </c:otherwise>
  </c:choose>

</div>
