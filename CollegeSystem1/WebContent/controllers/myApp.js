var myApp = angular.module('myApp',[]);
myApp.controller('myCtrl',function($scope,$http){
	$scope.hello = "hello";
	$scope.model = {};//{"roll":"8","name":"abc","add":"adfjdklja","phno":"9999999","gender":"male","dob":"1990-09-09","doa":"1990-09-09","stud_class":"BE"};
	/*$scope.model.roll="5";
	$scope.model.name="abc";*/
	$scope.saveStudent = function(){
		$http({
			method: 'POST',
			url: 'student',
			headers: {'content-type':'application/json'},
			data: $scope.model
		}).success(function(data){
			$scope.status = data;
		});
	};
	$scope.studentList = [];
	$scope.getStudentList = function(){
		$http({
			method: 'GET',
			url: 'student',
			headers: {'content-type':'application/json'},
			//data: $scope.model
		}).success(function(data){
			$scope.studentList = data;
		});
	};
});