angular.module('StudentApp', ['ui.router', 'ngAnimate'])//,'ngCookies'

	.config(function($stateProvider, $urlRouterProvider)
	{
		$stateProvider
		.state('home', {
			name: 'home',
			url: '/',
			templateUrl:'reg.html'
		})
				.state('reg', {
				name: 'reg',
				url: '/reg',
				templateUrl:'reg.html'
			})

			/*.state('tables', {
				name: 'tables',
				url: '/tables',
				templateUrl:'tables.html'	
			})*/

			.state('forgotPassword', {
				name: 'forgotPassword',
				url: '/forgotPassword',
				templateUrl:'forgotPassword.html'
			});
			
	})

	.controller('StudentController', function( $scope, $http, $window, $state, studentListService, $cookies)//
	{	
		$(document).ready(function(){
			//$('[data-toggle="tooltip"]').tooltip();
			
		});
		
		if(studentListService.getStudList() != null ||studentListService.getStudList() != undefined){
			$scope.studList = studentListService.getStudList();
		}
		
		$scope.ss1 = $cookies.getObject("ss");
		
		$scope.sl = JSON.parse(localStorage.getItem("studentlist"));//$cookies.get("ss");
		$scope.model={};
		$scope.registerUser = function(){
			$http({
				method: 'POST',
				url: 'registerUser',
				headers: {'content-type':'application/json'},
				params:{'register':'registerUser'},
				data: $scope.model
			}).success(function(data){
				$scope.status = data;
				$state.go('loginPage');
			});		
		};
		
		$scope.gotoLoginPage = function(){
			$state.go('reg');
		}

		$scope.register = function()
		{
			$state.go( 'register' );
		}

		$scope.forgotPassword = function()
		{
			$state.go( 'forgotPassword' );
		}
		$scope.error = false;
		$scope.validateUser = function(){
			$http({
				method: 'POST',
				url: 'registerUser',
				headers: {'content-type':'application/json'},
				params:{'authUser':'authUser'},
				data: $scope.model
			}).success(function(data){
				$scope.validUser = data;
				if($scope.validUser.email != undefined &&
						$scope.validUser.pass != undefined ){
					$state.go('tables.smart');
				}
				
			});	
		}
		
	});