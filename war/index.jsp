<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/noCache.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja" ng-app>
<head>
<meta charset="utf-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<!-- <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js'></script> -->
<script src="js/bootstrap.min.js"></script>
<script src="js/json2.js"></script>
<script src="http://code.angularjs.org/angular-1.0.1.min.js"></script>
<script type="text/javascript">
</script>
<title>メンバー管理</title>
</head>
<body id="content" ng-controller="mainCtrl" style="padding:50px;">
<div class="container">

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
    <div class="container">
        <a href="" class="brand">隠武士乃里 <small>/ 里乃民管理</small></a>
        <ul class="nav">
            <li class="active"><a href="">Home</a></li>
            <li><a href="/MemberManager/">里乃民一覧</a></li>
            <li><a href="/Join/">里加入</a></li>
        </ul>
        <form class="navbar-search pull-left">
            <input type="text" class="search-query" placeholder="検索">
        </form>
        <c:if test="${loginUserInfo != null}">
        <ul class="nav pull-right">
            <li class="dropdown">
                <a href="" class="dropdown-toggle" data-toggle="dropdown">
                ${loginUserInfo.nickname}
                <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="">Settings</a></li>
                    <li><a href="${logoutUrlPath}">Logout</a></li>
                </ul>
            </li>
        </ul>
        </c:if>
    </div>
    </div>
</div>


<div class="span11">


<div class="hero-unit">
  <h1>隠武士乃里</h1>  <p>隠れモノノフの里はGoogleのSNS、Google+をベースとしたももクロちゃん好きの集まりです。<br/>コンサート参戦、グッズ製作、イベントなどを通じて自由に交流を深めましょう！</p>
  <p>
    <a class="btn btn-primary btn-large" href="http://momoclo.in"/>
      里のオフィシャルサイトへ
    </a>
  </p>
</div>

<div class="page-header">
  <h4>このサイト（隠武士乃里&nbsp;/&nbsp;里乃民管理）は里への加入申請など、里のメンバー管理のための専用サイトです。 <br/><small>一部ページは管理者しか閲覧できません。</small></h4>
</div>

</div>
</body>
</html>
