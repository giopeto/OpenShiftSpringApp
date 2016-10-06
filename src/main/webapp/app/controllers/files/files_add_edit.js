'use strict';

/* Items Controller */

ngApp.lazy.controller('filesCtrl', function($scope, $log, $routeParams, $location, ItemFactory) {
	var vm = this;

	vm.obj = {};

	if ($routeParams.id > 0) {
		vm.obj = ItemFactory.get({ id: $routeParams.id }, function (data) {
		}, function (error) {
			$log.log ("Error: ", error);
		});
	}



});

