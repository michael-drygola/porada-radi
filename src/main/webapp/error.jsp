<%@ page language="java" isErrorPage="true" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<head><title>Doh!</title></head>

An Error has occurred in this application.

<% if (exception != null) { %>
    Please check your log files for further information.
    <% System.err.println(exception); %>
<% } %>
