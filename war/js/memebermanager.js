
var mainCtrl = function($scope, $http) {

  	$scope.updateMemberList = function() {
  	    var uri ='/MemberManager/GetMemberList?callback=JSON_CALLBACK';
  	    $http.jsonp(uri).
	    	success(function(data) {
    			$scope.members = data;

    			// G+APIから情報取得
    			for (var i = 0; i < $scope.members.length; i++) {
    				console.log(i);
    				var gplusUrl = "https://www.googleapis.com/plus/v1/people/" + $scope.members[i].gplusId + "?key=AIzaSyA7fDXepKFrWrp-AGHgi2yWVYQL6VGEjbc&callback=JSON_CALLBACK"
    				$http.jsonp(gplusUrl).
    					success(function(data) {
    						// 非同期なのでiは使えない。
    						// 最初に取得したmembersのG+IDとG+APIから返ってきたデータのG+IDを比較してセットする。
    						for(var j = 0; j < $scope.members.length; j++) {
    							if(data.id == $scope.members[j].gplusId) {
    	     						$scope.members[j].gplusInfo = data;
    							}
    						}
    					}).
    					error(function(data) {
    						console.log("G+アクセスエラー");
    					});
    			}
  	    	}).
  	    	error(function(data) {
    			$scope.member = data || "error";
	    		alert("updateMemberListのエラー");
  	    	});
  	}

  	$scope.getLoginMember = function() {
  	    var uri ='/MemberManager/GetLoginMember?callback=JSON_CALLBACK';
  	    $http.jsonp(uri).
	    	success(function(data) {
    			$scope.loginMember = data;
  	    	}).
  	    	error(function(data) {
    			$scope.loginMember = data || "error";
	    		alert("getLoginMemberのエラー");
  	    	});
  	}

  	$scope.showConfirm = function(targetMember, mode) {
  		// 承認対象のG+IDを保管
  		$scope.targetMember = targetMember;
  		$scope.approveConfirmMessage = mode == "changeApproveState"? (targetMember.approved ?  "の承認を取り消しますか？" : "を承認しますか？") : "を削除しますか？";
  		
  		$scope.modeApproveOrDelete = mode;
  		// 確認ダイアログ表示
  		$('#confirmDialog').modal('show');

  	}
  	
  	$scope.showEditForm = function(targetMember) {
  		// 承認対象のG+IDを保管
  		$scope.input = jQuery.extend(true, {}, targetMember);
  		// true, falseを文字列の"true", "false"に変換する。ラジオボタンのvalueは文字列なので、booleanをセットしてもチェックが入らないため。
  		for(var i in $scope.input) {
  			if($scope.input[i] === true || $scope.input[i] === false) {
  				$scope.input[i] = "" + $scope.input[i];//"true";
  			}
  		}
  		
  		// 確認ダイアログ表示
  		$('#editFormDialog').modal('show');
  	}
  	
  	// 承認リクエスト
  	$scope.approve = function() {
  		
  		// 承認処理
  		var uri ='/MemberManager/Approve?gplusId=' + $scope.targetMember.gplusId + '&approved=' + $scope.targetMember.approved + '&callback=JSON_CALLBACK';
  	    $http.jsonp(uri).
	    	success(function(data) {
	      		$scope.uploadResult = data;
	    		$('#alertbox').show();
	      		$scope.updateMemberList();
  	    	}).
  	    	error(function(data) {
    			$scope.member = data || "error";
	    		alert("approveのエラー");
  	    	});
  		$scope.targetMember = null;
  		$('#confirmDialog').modal('hide');
  	}
/*
  	$scope.upload =function() {
  	    var uri ='/MemberManager/UploadCsv?callback=JSON_CALLBACK';
  	    $http.jsonp(uri).
	    	success(function(data) {
    			$scope.updateMemberList();
  	    	}).
  	    	error(function(data) {
    			$scope.updateMemberList();
	    		alert("uploadのエラー");
  	    	});
  	}
*/
  	$scope.setFiles = function(element) {
  	    $scope.$apply(function($scope) {
  	      console.log('files:', element.files);
  	      // Turn the FileList object into an Array
  	        $scope.files = []
  	        for (var i = 0; i < element.files.length; i++) {
  	          $scope.files.push(element.files[i])
  	        }
  	      $scope.progressVisible = false
  	      });
  	    };
  	$scope.upload = function() {
        var fd = new FormData()
        for (var i = 0; i < $scope.files.length; i++) {
            fd.append("uploadedFile", $scope.files[i])
        }
        var xhr = new XMLHttpRequest()
//        xhr.upload.addEventListener("progress", uploadProgress, false)
        xhr.addEventListener("load", uploadComplete, false)
        xhr.addEventListener("error", uploadFailed, false)
        xhr.addEventListener("abort", uploadCanceled, false)
        xhr.open("POST", "/MemberManager/UploadCsv")
        $scope.progressVisible = true
        xhr.send(fd)
    }

  	function uploadComplete(evt) {
        /* This event is raised when the server send back a response */
  		$scope.updateMemberList();
  		$scope.uploadResult = JSON.parse(evt.target.responseText);
  		$('#alertbox').show();
    }

    function uploadFailed(evt) {
        alert("There was an error attempting to upload the file.")
    }

    function uploadCanceled(evt) {
        $scope.$apply(function(){
            scope.progressVisible = false
        })
        alert("The upload has been canceled by the user or the browser dropped the connection.")
    }

    $scope.download = function() {
    	location.href = "/MemberManager/DownloadCsv";
    }
    
    $scope.search = function() {
  	    var uri ='/MemberManager/SearchMember?query=' + $scope.searchQuery + '&callback=JSON_CALLBACK';
  	    $http.jsonp(uri).
	    	success(function(data) {
    			$scope.members = data;

    			// G+APIから情報取得
    			for (var i = 0; i < $scope.members.length; i++) {

    				var gplusUrl = "https://www.googleapis.com/plus/v1/people/" + $scope.members[i].gplusId + "?key=AIzaSyA7fDXepKFrWrp-AGHgi2yWVYQL6VGEjbc&callback=JSON_CALLBACK"
    				$http.jsonp(gplusUrl).
    					success(function(data) {
    						// 非同期なのでiは使えない。
    						// 最初に取得したmembersのG+IDとG+APIから返ってきたデータのG+IDを比較してセットする。
    						for(var j = 0; j < $scope.members.length; j++) {
    							if(data.id == $scope.members[j].gplusId) {
    	     						$scope.members[j].gplusInfo = data;
    							}
    						}
    					}).
    					error(function(data) {
    						console.log("G+アクセスエラー");
    					});
    			}
  	    	}).
  	    	error(function(data) {
    			$scope.member = data || "error";
	    		alert("searchのエラー");
  	    	});

    }
    
    $scope.doApproveOrDelete = function(mode) {
    	if (mode == 'approve') {
    		$scope.approve();
    	} else if (mode == 'delete') {
    		$scope.deleteMember();
    	}
    }
    
    $scope.deleteMember = function() {
  		// 承認処理
  		var uri ='/MemberManager/DeleteMember?gplusId=' + $scope.targetMember.gplusId + '&callback=JSON_CALLBACK';
  	    $http.jsonp(uri).
	    	success(function(data) {
	      		$scope.uploadResult = data;
	    		$('#alertbox').show();
	      		$scope.updateMemberList();
  	    	}).
  	    	error(function(data) {
    			$scope.member = data || "error";
	    		alert("approveのエラー");
  	    	});
  		$scope.targetMember = null;
  		$('#confirmDialog').modal('hide');

    }
    
    $scope.updateMember = function() {
    	alert("");
    }
}

// DOMの準備完了時
angular.element(document).ready(function() {
	//ドキュメント読み込み時にメンバーリストを読み込む
	var $scope = angular.element('#content').scope();
	$scope.updateMemberList();
	
	// ログインユーザのメンバー情報を取得
	$scope.getLoginMember();
	
	// その他イベント追加
	//ファイル選択時にテキストボックスにパスをコピー
	$('input[id=fileSelector]').change(function() {
		   $('#filePath').val($(this).val());
		});

	// クリックでアラートボックスを非表示にする
	$('.alert .close').live('click',function(){
		  $(this).parent().hide();
		});

	// テーブルのソートの準備
	new Tablesort(document.getElementById('memberListTable'));
});


// 承認状態を表すfilter
angular.module('memberManagerModule', []);
angular.module('memberManagerModule', [])
.filter('approveStateStringFilter', function () {
    return function (approved) {
    	if (approved) {
//    		return "済";
    		return '<i class="icon-ok"></i> 済';
    	} else {
//    		return "未";
    		return '<i class="icon-ban-circle"></i> 未';
    	}
    };
});
angular.module('MemberManager', ['memberManagerModule']);

