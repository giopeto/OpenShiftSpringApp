'use strict';

/* Groups Controller */

ngApp.lazy.controller('groupsCtrl', function($scope, $log, $location, $routeParams, GroupFactory) {
	var vm = this;
	vm.isLoading = false;
	vm.obj = {};
	vm.allObj = [];


	vm.save = save;
	vm.get = get;
	vm.update = update;
	vm.addEdit = addEdit;
	vm.remove = remove;

	function save () {
		changeLoadingState();
		GroupFactory.save(vm.obj, function (data) {
			goBack();
		}, function (error) {
			$log.log("Error: ", error);
			changeLoadingState();
		});
	};

	function get () {
		changeLoadingState();
		vm.allObj = GroupFactory.query({}, function() {
			changeLoadingState();
		}, function (error) {
			$log.log ("Error: ", error);
			changeLoadingState();
		});
	};

	function update () {
		changeLoadingState();
		GroupFactory.update(vm.obj, function (data) {
			goBack();
		}, function (error) {
			$log.log("Error: ", error);
			changeLoadingState();
		});
	};

	function remove (args) {
		changeLoadingState();
		GroupFactory.delete(args, function (data) {
			vm.allObj.splice(args.index, 1);
			changeLoadingState();
		}, function (error) {
			$log.log ("Error: ", error);
			changeLoadingState();
		});
	};

	function addEdit (args){
		var id = args.id ? args.id : null;
		$location.path('/groups_add_edit/'+id);
	};

	function goBack () {
		$location.path('/groups');
	};

	function changeLoadingState(){
		vm.isLoading = !vm.isLoading;
	};

	if ($routeParams.id) {
		changeLoadingState();
		vm.obj = GroupFactory.get({ id: $routeParams.id }, function (data) {
			changeLoadingState();
		}, function (error) {
			$log.log ("Error: ", error);
			changeLoadingState();
		});
	} else {
		get();
	}

});