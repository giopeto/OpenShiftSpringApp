'use strict';

/* Account Controller */

ngApp.lazy.controller('accountsCtrl', function ($scope, $log, $location, $http, AccountFactory, localStorageService, PSFactory) {
    var vm = this;
    vm.isLoading = false;
    vm.showSignIn = 1;
    vm.signup = signup;
    vm.signin = signin;
    vm.authenticate = authenticate;

    function signup() {
        AccountFactory.save(vm.obj, function (data) {
            authenticate();
        }, function (error) {
            $log.log("Error: ", error);
        });

    }

    function signin() {
        $http.post('/accounts/signin', vm.obj).success(function (data) {
            authenticate();
        }).error(function (error) {
            $log.log("ERROR signin: ", error);
        });
    }

    function authenticate() {
        $http.get('/accounts/').success(function (data) {
            $scope.main.user = data;
            if (data.id) {
                localStorageService.set("user", data);
                PSFactory.save({userId: $scope.main.user.id}, function (data) {
                    localStorageService.set("psId", data.id);
                    angular.copy(data, $scope.main.ps);
                }, function (error) {
                    $log.log("Error: ", error);
                });
            } else {
                localStorageService.remove("user");
                $scope.main.user = {};
            }
            return data;
        }).error(function (error) {
            $log.log("ERROR authenticate: ", error);
            $scope.main.user = {};
        });
    }

});
