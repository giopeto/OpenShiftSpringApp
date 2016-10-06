'use strict';

/* Items Controller */

ngApp.lazy.controller('loginCtrl', function($scope, $log, $http, $auth, localStorageService, PSFactory) {
	var vm = this;

	vm.obj = {};

	vm.authenticate = authenticate;
	vm.logout = logout;

	 function authenticate (provider) {

		$auth.authenticate(provider)
			.then(function(response) {
				$log.log ("$auth.isAuthenticated(): ", $auth.isAuthenticated());
				// exchangeCodeForToken(response.config.data);

				$log.log (response.data);

				$log.log("Save also token to user and return here yhen to auth.setToken or something similar.");

				vm.obj = response.data;
				angular.copy(vm.obj, $scope.main.user);
				localStorageService.set("user", vm.obj);
				PSFactory.save({userId: $scope.main.user.id}, function (data) {
					localStorageService.set("psId", data.id);
					angular.copy(data, $scope.main.ps);
				}, function (error) {
					$log.log("Error: ", error);
				});






			})
			.catch(function(response) {
				// Something went wrong.
			});

	};


	function logout () {

		var logOutUrl = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token="+vm.obj.accessToken;
		$http.post(logOutUrl, {
		}).success(function(response) {
		}).error(function(error) {
			$log.log("ERROR: ", error);
		});



	}



});

