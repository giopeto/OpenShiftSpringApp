'use strict';

/* Items Controller */

ngApp.lazy.controller('itemsCtrl', function($rootScope, $scope, $log, $routeParams, $location, $http, ItemFactory, GroupFactory, PSFactory, localStorageService, UserFactory) {
	var vm = this;

	vm.isLoading = false;
	vm.errorMsg = {};
	vm.obj = {
		fileIds: []
	};
	vm.allObj = [];
	vm.allGroups =  [];
	vm.allGroups =  GroupFactory.query();

	vm.allUsersAsHash = {};

	vm.save = save;
	vm.get = get;
	vm.update = update;
	vm.addEdit = addEdit;
	vm.info = info;
	vm.remove = remove;
	vm.changeFilesOrder = changeFilesOrder;
	vm.addToBasket = addToBasket;
	vm.addComment = addComment;
	vm.removeFile = removeFile;

	var skipGoBack = 0;

	function save () {

		if (!vm.obj.groupId) {
			vm.errorMsg.groupErr = 1;
			return;
		}

		changeLoadingState();
		ItemFactory.save(vm.obj, function () {
			goBack();
		}, function (error) {
			$log.log("Error: ", error);
			changeLoadingState();
		});
	};

	function update () {
		changeLoadingState();
		ItemFactory.update(vm.obj, function (data) {
			if (!skipGoBack) {
				goBack();
			}
		}, function (error) {
			$log.log("Error: ", error);
			changeLoadingState();
		});
	};

	function remove (args) {
		changeLoadingState();
		ItemFactory.delete(args, function (data) {
			vm.allObj.splice(args.index, 1);
			changeLoadingState();
		}, function (error) {
			$log.log ("Error: ", error);
			changeLoadingState();
		});
	};

	function get () {
		changeLoadingState();
		vm.allObj = ItemFactory.query({}, function() {
			changeLoadingState();
		}, function (error) {
			$log.log ("Error: ", error);
			changeLoadingState();
		});
	};

	function addEdit (args){
		var id = args.id ? args.id : "new";
		$location.path('/items_add_edit/'+id);
	};

	function info (args){
		$location.path('/item_info/'+args.id);
	};

	function goBack () {
		$location.path('/items');
	};

	function changeLoadingState(){
		vm.isLoading = !vm.isLoading;
	};

	function changeFilesOrder (args) {
		/*var tmpArr = [];
		angular.copy(vm.obj.aFileIds, tmpArr);

		if (args.direction === 'up') {
			tmpArr[args.index] = vm.obj.aFileIds[args.index-1];
			tmpArr[args.index-1] = vm.obj.aFileIds[args.index];
		} else {
			tmpArr[args.index] = vm.obj.aFileIds[args.index+1];
			tmpArr[args.index+1] = vm.obj.aFileIds[args.index];
		}

		vm.obj.aFileIds = tmpArr;*/
	};

	function removeFile (args) {
		//vm.obj.aFileIds.splice(args.index, 1);
	};


	function addToBasket (args) {
		changeLoadingState();

		args.quantity = 1;
		$scope.main.ps.items.push(args);
		PSFactory.update($scope.main.ps, function () {
			changeLoadingState();
			alert ("Success")
		}, function (error) {
			$log.log("Error: ", error);
			changeLoadingState();
		});

	};

	function getAllUsers () {
		vm.allUsers = UserFactory.query().$promise.then(function(data){
			data.forEach(function (v) {
				vm.allUsersAsHash[v.id] = v;
			});
		})
	}

	function addComment (args) {
		//args.row.comments = [];
		if (!args.comment) {
			return;
		}
		args.row.comments.unshift({userId: $scope.main.user.id, comment: args.comment});
		vm.obj = args.row;
		skipGoBack = 1;
		args.row.comment = '';
		update();
	};

	$rootScope.$on('filesChanged', function(args, data){

		$log.log (data);

		vm.obj.fileIds.push(data.fileId);
	});

	if ($routeParams.id && $routeParams.id != "new") {
		changeLoadingState();
		vm.obj = ItemFactory.get({ id: $routeParams.id }, function (data) {
			changeLoadingState();
			console.log (vm.obj);

		}, function (error) {
			$log.log ("Error: ", error);
			changeLoadingState();
		});
		getAllUsers();
	} else if ($routeParams.groupId){
		changeLoadingState();
		$http.get('items/getByGroupId/'+$routeParams.groupId, {

		}).success(function(data) {
			vm.allObj = data;
			changeLoadingState();
		}).error(function(error) {
			$log.log("ERROR: "+error);
			changeLoadingState();
		});
	} else {

		get();
	}



});

