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
<link href="/css/tablesort.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<!-- <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js'></script> -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/json2.js"></script>
<script src="/js/tablesort.min.js"></script>
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
        <form class="navbar-search pull-left"  ng-submit="search()" name="searchForm">
            <input type="text" class="search-query" placeholder="検索" ng-model="searchQuery">
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
<table class="table table-striped table-hover" id="memberListTable">
<thead>
<tr>
<th ng-show="loginMember.admin">承認</th>
<th>出席番号</th>
<th>G+</th>
<th>Twitter</th>
<th>組</th>
<th>推し</th>
<th>PR</th>
<th>その他</th>
<th></th>
</tr>
</thead>
<tbody>
<tr ng-repeat="member in members" >
	<td ng-show="loginMember.admin"><a href="" ng-click="showConfirm(member, 'approve')" ng-bind-html-unsafe="member.approved | approveStateStringFilter"></a></td>
	<td>{{member.shussekiBango}}</td>
	<td><a href="{{member.gplusInfo.url}}" target="_blank"><img src="{{member.gplusInfo.image.url}}"/> {{member.gplusInfo.displayName}}</a></td>
	<td>{{member.twitterId}}</td>
	<td>{{member.kumi}}</td>
	<td>{{member.oshi}}</td>
	<td>{{member.pr}}</td>
	<td>{{member.memo}}</td>
	<td>
		<a href="" class="btn" ng-click="showEditForm(member)"><i class="icon-pencil"></i></a>
		<a href="" class="btn" ng-click="showConfirm(member, 'delete')"><i class="icon-remove"></i></a>
	</td>
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
<div id="confirmDialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modalDialogLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3 id="modalDialogLabel">確認</h3>
  </div>
  <div class="modal-body">
    <p>{{targetMember.gplusInfo.displayName}} さん{{approveConfirmMessage}}</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">キャンセル</button>
    <button class="btn btn-primary" ng-click="doApproveOrDelete()">実行！</button>
  </div>
</div>
<!-- Modal -->

<!-- Modal -->
<div id="editFormDialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modalFormLabel" aria-hidden="true" style="width:700px;margin: -300px 0 0 -350px;">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3 id="modalDialogLabel">民情報編集</h3>
  </div>
  <div class="modal-body">
		
	<form ng-submit="updateMember()" name="editForm" id="editForm">
		<div class="control-group">
		<label class="control-label">G+</label>
		<div class="controls">
		<input type="text" name="gplusId" class="input4edit span3" placeholder="Enter G+ ID" ng-model="input.gplusId" required ng-pattern="/^\d{21}$/"/>
		<span class="error" ng-show="editForm.gplusId.$error.required">必須</span>
		<span class="error" ng-show="editForm.gplusId.$error.pattern">21文字の数字を入力してください!（<a href="https://plus.google.com/me" target="blank">https://plus.google.com/meにアクセスしてURLの数字の部分をコピペ！）</a></span><br />
		</div>
		</div>
		
		<div class="control-group">
		<label class="control-label">メールアドレス</label>
		<div class="controls">
		<input type="email" name="mail" class="input4edit span3" placeholder="Enter e-mail address" ng-model="input.mail" required/>
		<span class="error" ng-show="editForm.mail.$error.required">必須</span>
		<span class="error" ng-show="editForm.mail.$error.email">正しいメールアドレスを入力してください。</span><br />
		</div>
		</div>
		
		<div class="control-group">
		<label class="control-label">Twitter</label>
		<div class="controls">
		<input type="text" name="twitterId" class="input4edit span3" placeholder="Enter Twitter ID" ng-model="input.twitterId" required ng-pattern="/(@[A-Za-z0-9_]{1,15})/" />
		<span class="error" ng-show="editForm.twitterId.$error.pattern">@英数字を入力してください。（15文字以内）</span><br />
		</div>
		</div>
		
		<div class="control-group">
		<div class="controls">
		<label class="radio inline">
		  <input type="radio" name="admin" class="input4edit" value="true" ng-model="input.admin" required> 管理者</label>
		<label class="radio inline">
		  <input type="radio" name="admin" class="input4edit" value="false" ng-model="input.admin" required> 一般</label>
		</div>
		</div>
		
		<div class="control-group">
		<div class="controls">
		<label class="radio inline">
		  <input type="radio" name="deletedDate" class="input4edit" value="true" ng-model="input.deletedDate" required> 削除</label>
		<label class="radio inline">
		  <input type="radio" name="deletedDate" class="input4edit" value="false" ng-model="input.deletedDate" required> 有効</label>
		</div>
		</div>
		
		<span ng-show="editForm.$invalid" class="comment">未入力の項目や正しく入力できていない項目があります。</span>
		<span ng-show="editForm.$valid" class="comment">入力チェックOK!</span>
	</form>
		
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">キャンセル</button>
	<input id="updatebtn" name="updatebtn" class="btn btn-primary" type="submit" value="更新！" ng-disabled="editForm.$invalid" form="editForm"/>
  </div>
</div>
<!-- Modal -->

</div>
<pre>{{input|json}}</pre>
<pre>{{targetMember|json}}</pre>
<pre>{{uploadResult|json}}</pre>
<pre>{{members|json}}</pre>
</body>
</html>
