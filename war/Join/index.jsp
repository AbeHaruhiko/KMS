<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/noCache.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja" ng-app>
<head>
<meta charset="utf-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<!-- <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js'></script> -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/json2.js"></script>
<script src="http://code.angularjs.org/angular-1.0.1.min.js"></script>
<script src="/js/join.js"></script>
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
            <li><a href="/">Home</a></li>
            <li><a href="/MemberManager/">里乃民一覧</a></li>
            <li class="active"><a href="">里加入</a></li>
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
                    <li><a href=""><i class="icon-cog"></i> Settings</a></li>
                    <li><a href="${logoutUrlPath}"><i class="icon-off"></i> Logout</a></li>
                </ul>
            </li>
        </ul>
        </c:if>
    </div>
    </div>
</div>


<div class="span11">

<div id="alertbox" class="alert alert-{{applyResult.status | lowercase}} hide linky" style="margin: 3px 0 3px 0;" ng-bind-html-unsafe="applyResult.message">
<!-- <button type="button" class="close" data-dismiss="alert">&times;</button> -->
<button type="button" class="close">&times;</button>
</div>
<script type="text/javascript">
$('.alert .close').live('click',function(){
	  $(this).parent().hide();
	});

</script>


<form ng-submit="apply()" name="applyForm">

<div class="control-group">
<label class="control-label">G+</label>
<div class="controls">
<input type="text" name="gplusId" class="input4apply span3" placeholder="Enter your G+ ID" ng-model="input.gplusId" required ng-pattern="/^\d{21}$/" />
<span class="error" ng-show="applyForm.gplusId.$error.required">必須</span>
<span class="error" ng-show="applyForm.gplusId.$error.pattern">21文字の数字を入力してください!（<a href="https://plus.google.com/me" target="blank">https://plus.google.com/meにアクセスしてURLの数字の部分をコピペ！）</a></span><br />
</div>
</div>

<div class="control-group">
<label class="control-label">メールアドレス</label>
<div class="controls">
<input type="email" name="mail" class="input4apply span3" placeholder="Enter your e-mail address" ng-model="input.mail" required/>
<span class="error" ng-show="applyForm.mail.$error.required">必須</span>
<span class="error" ng-show="applyForm.mail.$error.email">正しいメールアドレスを入力してください。</span><br />
</div>
</div>

<div class="control-group">
<label class="control-label">Twitter</label>
<div class="controls">
<input type="text" name="twitterId" class="input4apply span3" placeholder="Enter your Twitter ID" ng-model="input.twitterId" required ng-pattern="/(@[A-Za-z0-9_]{1,15})/" />
<span class="error" ng-show="applyForm.twitterId.$error.pattern">@英数字を入力してください。（15文字以内）</span><br />
</div>
</div>

<div class="control-group">
<label class="control-label">管理者</label>
<div class="controls">
<label class="radio">
  <input type="radio" name="isAdmin" id="isAdmin" value="true" required>  管理者</label>
<label class="radio">
  <input type="radio" name="isAdmin" id="isAdminFalse" value="false" required>  民</label>
<!-- <span class="error" ng-show="applyForm.isAdmin.$error.required">必須</span><br /> -->
</div>
</div>

<input id="applybtn" name="applybtn" class="btn" type="submit" value="入里申請！" ng-disabled="applyForm.$invalid"/>
<span ng-show="applyForm.$invalid" class="comment">未入力の項目や正しく入力できていない項目があります。</span>
<span ng-show="applyForm.$valid" class="comment">入力チェックOK!</span>

</form>


</div>
</div>


</div>
<pre>{{input|json}}</pre>
<pre>{{gplusInfo|json}}</pre>
<pre>{{applyResult|json}}</pre>
</body>
</html>
