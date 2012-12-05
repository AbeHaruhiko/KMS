<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/noCache.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja" ng-app="MemberManager">
<head>
<meta charset="uft-8">
<link href="/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/bootstrap-responsive.min.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<!-- <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js'></script> -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/json2.js"></script>
<script src="http://code.angularjs.org/angular-1.0.1.min.js"></script>
<script src="/js/memebermanager.js" charset="utf-8"></script>
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
            <li class="active"><a href="">里乃民一覧</a></li>
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

<input id="fileSelector" name="fileSelector" type="file" style="display:none" onchange="angular.element(this).scope().setFiles(this);">
<span class="input-append" style="margin-right:5px;">
	<input id="filePath" class="input-large" type="text">
	<a class="btn" onclick="$('input[id=fileSelector]').click();">ファイル選択</a>
	<button class="btn" ng-click="upload()"><i class="icon-upload"></i> CSV Import</button>
</span>
<button class="btn" ng-click="download()"><i class="icon-download"></i> CSV Export</button>

<div id="alertbox" class="alert alert-{{uploadResult.status | lowercase}} hide" style="margin: 3px 0 3px 0;">
<!-- <button type="button" class="close" data-dismiss="alert">&times;</button> -->
<button type="button" class="close">&times;</button>
{{uploadResult.message}}
</div>

<p>現在の里の人口は{{members.length}}人！！</p>
<table class="table table-striped table-hover">
<thead>
<tr><c:if test="${loginMemberInfo.admin}"><th>承認</th></c:if><th>出席番号</th><th>G+</th><th>Twitter</th><th>組</th><th>推し</th><th>PR</th><th>その他</th></tr>
</thead>
<tbody>
<tr ng-repeat="member in members" >
<c:if test="${loginMemberInfo.admin}"><td><a href="" ng-click="showComfirm(member)">{{member.approved | approveStateStringFilter}}</a></td></c:if><td>{{member.shussekiBango}}</td><td><a href="{{member.gplusInfo.url}}" target="_blank"><img src="{{member.gplusInfo.image.url}}"/> {{member.gplusInfo.displayName}}</a></td><td>{{member.twitterId}}</td><td>{{member.kumi}}</td><td>{{member.oshi}}</td><td>{{member.pr}}</td><td>{{member.memo}}</td>
</tr>
</tbody>
</table>

<div class="pagination">
<ul>
<li class="disabled"><a href="">Prev</a></li>
<li class="active"><a href="">1</a></li>
<li><a href="">2</a></li>
<li><a href="">Next</a></li>
</ul>
</div>

<p><span class="label label-info">NOTE</span> Message ... <span class="badge badge-important">5</span></p>
</div>

<!-- Modal -->
<div id="modalDialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modalDialogLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3 id="modalDialogLabel">確認</h3>
  </div>
  <div class="modal-body">
    <p>{{targetMember.gplusInfo.displayName}} さん{{approveComfirmMessage}}</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">キャンセル</button>
    <button class="btn btn-primary" ng-click="approve()">実行！</button>
  </div>
</div>

</div>
<pre>{{uploadResult|json}}</pre>
<pre>{{members|json}}</pre>
</body>
</html>
