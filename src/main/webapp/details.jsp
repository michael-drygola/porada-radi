<%@ include file="/taglibs.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<div class="future-laws sixteen columns">
  <h2>законопроект#${law.id}</h2>
      <table border="0">
        <tr>
        <th>№</th><th>назва</th><th>дата</th>
        </tr>
          <tr>
              <td>${law.id}</td><td><a href="${law.link}">${law.caption}</a></td><td>08.01.13</td>
          </tr>
      </table>
</div>
