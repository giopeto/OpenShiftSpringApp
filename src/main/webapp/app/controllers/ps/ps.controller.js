'use strict';

/* PS Controller */

ngApp.lazy.controller('psCtrl', function($scope, $log, $routeParams, $location, localStorageService, PSFactory) {
	var vm = this;

	vm.isLoading = false;
	vm.errorMsg = {};
	vm.obj = {};

	//vm.save = save;
	//vm.get = get;
	vm.removeItem = removeItem;


	function save () {
		PSFactory.update(vm.obj, function () {
			//changeLoadingState();
			alert ("Success")
		}, function (error) {
			$log.log("Error: ", error);
			//changeLoadingState();
		});
	};

	function removeItem (index) {
		$log.log("index: ", index);
		vm.obj.items.splice(index, 1);
		save ();
	};

	$log.log($routeParams);
	if ($routeParams.id > 0) {
		vm.obj = PSFactory.query({id: $routeParams.id})
	} else {
		//get();
	}



});

