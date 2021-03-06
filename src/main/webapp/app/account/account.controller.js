'use strict';

/* Account Controller */

ngApp.lazy.controller('accountsCtrl', function ($scope, $log, $location, $http, AccountFactory, localStorageService, PSFactory, $rootScope) {
    var vm = this;
    vm.message = "";
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
                vm.message = "Incorrect email or password";
            }
            return data;
        }).error(function (error) {
            $log.log("ERROR authenticate: ", error);
            $scope.main.user = {};
        });
    }

    $rootScope.$on('filesChanged', function (args, data) {
        if(data.metadata.type !== 'user'){
            return;
        }

        $scope.main.user.fileId = data.fileId;

        AccountFactory.update($scope.main.user, function (data) {
            localStorageService.set("user", $scope.main.user);
        }).error(function (error) {

            $log.log(error);
            $log.log(JSON.stringify(error));
            $log.log(JSON.parse(error));

            $log.log("ERROR signin: ", JSON.stringify(error));
        });
    });

    /*if ($routeParams.id) {
        $log.log ('ASD');
    }*/
});
