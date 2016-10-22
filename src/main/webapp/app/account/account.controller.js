'use strict';

/* Users Controller */

ngApp.lazy.controller('accountsCtrl', function($scope, $log, $location, $http, AccountFactory, localStorageService) {
	var vm = this;
	vm.isLoading = false;
	vm.showSignIn = 1;
	vm.signup = signup;
	vm.signin = signin;
	vm.authenticate = authenticate;

	function signup () {
		AccountFactory.save(vm.obj, function (data) {
			angular.extend(vm.obj, data);
			vm.obj.message = data.message;
			authenticate ();
		}, function (error) {
			$log.log("Error: ", error);

		});

	}

	function signin () {
		$http.post('/accounts/signin', vm.obj).success(function(data) {
			angular.extend(vm.obj, data);
			vm.obj.message = data.message;
			authenticate ();
		}).error(function(error) {
			$log.log("ERROR signin: ", error);
		});
	}

	function authenticate () {
		$http.get('/accounts/').success(function(data) {
			localStorageService.set("user", data);
			$scope.main.user = data;
			return data;
		}).error(function(error) {
			$log.log("ERROR authenticate: ", error);
			localStorageService.remove("user");
			$scope.main.user = {};
		});
	}

});