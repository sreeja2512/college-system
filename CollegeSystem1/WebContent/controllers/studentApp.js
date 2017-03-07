angular.module('StudentApp', ['ui.router', 'ngAnimate'])

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

	.controller('StudentController', function( $scope, $http, $window, $state )
	{	
		$(document).ready(function(){
			//$('[data-toggle="tooltip"]').tooltip();
			
		});
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