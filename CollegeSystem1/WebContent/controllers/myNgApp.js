var myNgApp = angular.module("myNgApp",['ui.router', 'ngAnimate','StudentApp','angularUtils.directives.dirPagination']);
myNgApp.config(function($stateProvider, $urlRouterProvider)
		{
	$stateProvider
	.state('addstudent', {
		name: 'addstudent',
		url: '/addStudents',
		templateUrl:'studentDetails.html'	
	})
		.state('listStudent', {
			name: 'listStudent',
			url: '/studentsList',
			templateUrl:'listStudentDetails.html'	
		});
	$urlRouterProvider.otherwise('/');
});
myNgApp.controller("studentDetails",function($scope,$http,$state,$rootScope,$filter){
	$scope.hello = "hello";
	$scope.disableRoll = false;
	$scope.reverseSort = false;
	$scope.edit = false;
	$scope.currentPage = 1;
	$scope.pageSize = 2;
	$scope.model = {};//{"roll":"8","name":"abc","add":"adfjdklja","phno":"9999999","gender":"male","dob":"1990-09-09","doa":"1990-09-09","stud_class":"BE"};
	$scope.sort = function(){
		//$scope.sortBy = "roll";
		$scope.reverseSort = !$scope.reverseSort;
		if($scope.reverseSort){
			$scope.sortBy = "name";
		}else{
			$scope.sortBy = "-name";
		}
		
	}
	if($rootScope.model !== undefined){
		$scope.model = $rootScope.model;
		$scope.model.rollno = $rootScope.model.roll;
		$scope.model.dob = $filter('date')(new Date($rootScope.model.dob),'yyyy-MM-dd')	;
		$scope.model.doa = $filter('date')(new Date($rootScope.model.doa),'yyyy-MM-dd')	;
		$scope.edit = true;
	}
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
			$state.go('listStudent');
		});		
	};
	
	$scope.updateStudent = function(){
		$http({
			method: 'POST',
			url: 'student',
			headers: {'content-type':'application/json'},
			params:{'update':'update'},
			data: $scope.model
		}).success(function(data){
			$scope.status = data;
			$state.go('listStudent');
		});		
	};
	
	$scope.enableStud = function(student){
		student.stud_status = "ENABLE";
		$http({
			method: 'POST',
			url: 'student',
			headers: {'content-type':'application/json'},
			params:{'enableStud':'enableStud'},
			data: student
		}).success(function(data){
			$scope.status = data;
			//$state.reload();
		});		
	};
	
	$scope.disableStud = function(student){
		student.stud_status = "DISABLE";
		$http({
			method: 'POST',
			url: 'student',
			headers: {'content-type':'application/json'},
			params:{'disableStud':'disableStud'},
			data: student
		}).success(function(data){
			$scope.status = data;
			//$state.reload();
		});		
	};
	
	$scope.editData = function(data){
		$rootScope.model = data;
		$state.go('form.layouts');
		//$state.go('addstudent');
		
		//model.rollno = data.roll;
		
		
	};
	
	$scope.gotoAddStuent = function(){
		$state.go('addstudent');
	}
	$scope.gotoListStuent = function(){
		$state.go('tables.smart');
	}
	$scope.studentList = [];
	$scope.getStudentList = function(){
		$http({
			method: 'GET',
			url: 'student',
			headers: {'content-type':'application/json'},
			//data: $scope.model
		}).success(function(data){
			$scope.studentList= data;
			for(var i=0;i<$scope.studentList.length;i++){
				$scope.studentList[i].dob = $filter('date')(new Date($scope.studentList[i].dob), 'yyyy-MM-dd');
				$scope.studentList[i].doa = $filter('date')(new Date($scope.studentList[i].doa), 'yyyy-MM-dd');
			}
		});
	};
	
});



