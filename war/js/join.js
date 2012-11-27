var mainCtrl = function($scope, $http) {

  	$scope.apply = function() {
  		// 申請項目を無効化
  		$('#applybtn').attr('disabled', true);
  		$('.input4apply').attr('readonly', true);

//		var post_data = $.param(angular.toJson($scope.input));
		var post_data = $.param($scope.input);

		$http({
			url: '/Join/Apply',
			method: 'POST',
			data: post_data,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			}
		}).
		success(function(data) {
			console.log('data: ' + data);
			$scope.applyResult = data;
			$('#alertbox').show();
		}).
		error(function(data) {
			$scope.applyResult = data || "error";
			alert("updateMemberListのエラー");
		});

//  		var uri ='/Join/Apply?callback=JSON_CALLBACK';
//  	    $http.jsonp(uri).
//	    	success(function(data) {
//    			$scope.applyResult = data;
//    			$('#alertbox').show();
//  	    	}).
//  	    	error(function(data) {
//    			$scope.applyResult = data || "error";
//	    		alert("updateMemberListのエラー");
//  	    	});
  	}
}
