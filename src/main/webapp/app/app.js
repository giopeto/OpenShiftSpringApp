//Define an angular module for our app
var ngApp = angular.module('ngApp', ['ngRoute', 'ngResource', 'satellizer']);

ngApp.controller('mainCtrl', function($scope, $http, $log, localStorageService, GroupFactory, PSFactory) {

	$scope.main = {
		user: localStorageService.get("user"),
		allGroups:  GroupFactory.query(),
		//ps: PSFactory.query({id: localStorageService.get("psId")}),
	};

	$scope.logOut = function () {
		$http.get('/accounts/logOut').success(function(data) {
			localStorageService.remove("user");
			$scope.main.user = {};
		}).error(function(error) {
			$log.log("ERROR: ", error);
			$scope.main.user = {};
		});
	};


});

ngApp.config(function ($controllerProvider, $compileProvider, $filterProvider, $provide, $routeProvider, $httpProvider, $locationProvider) {

	$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	$locationProvider.html5Mode = true;

	ngApp.lazy = {
        controller: $controllerProvider.register,
       	directive: $compileProvider.directive,
        filter: $filterProvider.register,
        factory: $provide.factory,
        service: $provide.service
    };

	$routeProvider.when('/items', {
		templateUrl: 'app/items/items.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/items/items.controller.js',
					'app/items/item.service.js',
					'app/ps/ps.service.js',
					'app/users/user.service.js'
					/*'app/services/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (err) {
						console.log ('RouteProvider resolve error: ', err );
					});
				});
				return deferred.promise;
			}]
		}

	}).when('/items_add_edit/:id', {
		templateUrl: 'app/items/items_add_edit.html',
			resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/items/items.controller.js',
					'app/items/item.service.js',
					'app/users/user.service.js',
					'app/files/files.directive.js',
					'app/files/files.controller.js'
					/*'app/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (err) {
						console.log ('RouteProvider resolve error: ', err );
					});
				});
				return deferred.promise;
			}]
		}

	}).when('/item_info/:id', {
		templateUrl: 'app/items/item_info.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/items/items.controller.js',
					'app/items/item.service.js',
					'app/users/user.service.js'
					/*'app/services/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (err) {
						console.log ('RouteProvider resolve error: ', err );
					});
				});
				return deferred.promise;
			}]
		}


	}).when('/items_list/:groupId', {
		templateUrl: 'app/items/items_list.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/items/items.controller.js',
					'app/items/item.service.js',
					'app/users/user.service.js'
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (err) {
						console.log ('RouteProvider resolve error: ', err );
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/groups', {
		templateUrl: 'app/groups/groups.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/groups/groups.controller.js',
					/*'app/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function () {
						console.log ('ERROR');
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/groups_add_edit/:id', {
		templateUrl: 'app/groups/groups_add_edit.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/groups/groups.controller.js',
					/*'app/services/groups/group.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function () {
						console.log ('ERROR');
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/files_add_edit/:id', {
		templateUrl: 'app/files/files_add_edit.jsp',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/files/files.controller.js',
					'app/items/item.service.js',
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}

	}).when('/ps_add_edit/:id', {
		templateUrl: 'app/ps/ps_add_edit.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/ps/ps.controller.js',
					/*'app/items/item.service.js',*/
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function (error) {
						console.log ('ERROR: ', error);
					});
				});
				return deferred.promise;
			}]
		}

	}).when('/signin', {
		templateUrl: 'app/account/signin.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/account/account.service.js',
					'app/account/account.controller.js'
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function () {
						console.log ('ERROR');
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/signup', {
		templateUrl: 'app/account/signup.html',
		resolve: {
			load: ['$q', '$rootScope', function ($q, $rootScope) {
				var deferred = $q.defer();
				require([
					'app/account/account.service.js',
					'app/account/account.controller.js'
				], function () {
					$rootScope.$apply(function () {
						deferred.resolve();
					}, function () {
						console.log ('ERROR');
					});
				});
				return deferred.promise;
			}]
		}
	}).when('/home', {
		templateUrl: 'app/home/home.html'

	}).otherwise({
		redirectTo: '/home',
	});



});