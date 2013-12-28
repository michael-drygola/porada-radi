<%@ include file="/taglibs.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <title>Порада раді</title>
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Mobile Specific Metas
  ================================================== -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

  <!-- CSS
  ================================================== -->
    <link rel="stylesheet" href="${ctx}/styles/base.css">
    <link rel="stylesheet" href="${ctx}/styles/skeleton.css">
    <link rel="stylesheet" href="${ctx}/styles/layout.css">
    <link rel="stylesheet" href="${ctx}/styles/table.css">
    <link rel="stylesheet" href="${ctx}/styles/page.css">

  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->


    <link rel="shortcut icon" href="${ctx}/images/favicon.ico">
    <link rel="apple-touch-icon" href="${ctx}/images/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="${ctx}/images/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="${ctx}/images/apple-touch-icon-114x114.png">

</head>
<body>
  <div class="container">
          <div class="eight columns">
      <h1 class="remove-bottom" style="margin-top: 40px">Порада Раді</h1>
            <p>Народна підказка, коли депутати не знають, як голосувати</p>
      <h5>Version 0.1</h5>
          </div>
          <div class="eight columns">
            <img src="images/kpd.jpg" alt="" />
          </div>
      <hr />
      <div class="content">
        <%@ include file="/messages.jsp"%>
        <decorator:body/>

        <decorator:getProperty property="page.underground"/>
      </div>
        <div class="footer center">
        <p>2013 Майдан</p>

        </div>

  </div><!-- container -->


<!-- End Document
================================================== -->

</body>



