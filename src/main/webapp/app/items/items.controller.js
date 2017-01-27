'use strict';

/* Items Controller */

ngApp.lazy.controller('itemsCtrl', function ($rootScope, $scope, $log, $routeParams, $location, $http, ItemFactory, GroupFactory, PSFactory, localStorageService) {
    var vm = this;

    vm.isLoading = false;
    vm.errorMsg = {};
    vm.showAlreadyOrderThisItemErr = false;
    vm.obj = {
        fileIds: []
    };
    vm.allObj = [];
    vm.allGroups = [];
    vm.allGroups = GroupFactory.query();

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
    vm.changeQuantity = changeQuantity;
    vm.swapImage = swapImage;

    var skipGoBack = 0;

    function save() {

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

    function update() {
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

    function remove(args) {
        changeLoadingState();
        ItemFactory.delete(args, function (data) {
            vm.allObj.splice(args.index, 1);
            changeLoadingState();
        }, function (error) {
            $log.log("Error: ", error);
            changeLoadingState();
        });
    };

    function get() {
        changeLoadingState();
        vm.allObj = ItemFactory.query({}, function () {
            changeLoadingState();
        }, function (error) {
            $log.log("Error: ", error);
            changeLoadingState();
        });
    };

    function addEdit(args) {
        var id = args.id ? args.id : "new";
        $location.path('/items_add_edit/' + id);
    };

    function info(args) {
        $location.path('/item_info/' + args.id);
    };

    function goBack() {
        $location.path('/items');
    };

    function changeLoadingState() {
        vm.isLoading = !vm.isLoading;
    };

    function changeFilesOrder(args) {
        var tmpArr = [];
        angular.copy(vm.obj.fileIds, tmpArr);

        if (args.direction === 'up') {
            tmpArr[args.index] = vm.obj.fileIds[args.index - 1];
            tmpArr[args.index - 1] = vm.obj.fileIds[args.index];
        } else {
            tmpArr[args.index] = vm.obj.fileIds[args.index + 1];
            tmpArr[args.index + 1] = vm.obj.fileIds[args.index];
        }

        vm.obj.fileIds = tmpArr;
    };

    function removeFile(args) {
        vm.obj.fileIds.splice(args.index, 1);
    };


    function addToBasket(args) {
        changeLoadingState();
        $log.log(args.quantity);

        if ($scope.main.ps.quantity[args.id]) {
            //alert ("OLD: " + $scope.main.ps.quantity[args.id] + ", NEW: "+ args.quantity);
            $scope.main.ps.quantity[args.id] = args.quantity;
        } else {
            $scope.main.ps.quantity[args.id] = args.quantity;
            $scope.main.ps.items.push(args);
        }


        PSFactory.update($scope.main.ps, function () {
            changeLoadingState();
            $log.log("Success");
        }, function (error) {
            $log.log("Error: ", error);
            changeLoadingState();
        });

    };

    function addComment(args) {
        if (!args.comment) {
            return;
        }

        args.row.comments.unshift({
			userId: $scope.main.user.id,
			comment: args.comment,
			userEmail: $scope.main.user.email,
			userFileId: $scope.main.user.fileId
		});
        vm.obj = args.row;
        skipGoBack = 1;
        args.row.comment = '';
        update();
    };

    function changeQuantity(args) {
        $log.log(args);
        if(args.tp==="increase"){
            args.quantity++;
        }else{
            args.quantity--;
        }
        if(args.quantity>0) {
            vm.obj.quantity = args.quantity;
        }
    }

    function swapImage(args){
        $log.log(args);
        var tempId = vm.obj.fileIds[0];
        vm.obj.fileIds[0]=args.id;
        args.id = tempId;
    }

    $rootScope.$on('filesChanged', function (args, data) {
        vm.obj.fileIds.push(data.fileId);
    });

    if ($routeParams.id && $routeParams.id != "new") {
        changeLoadingState();
        vm.obj = ItemFactory.get({id: $routeParams.id}, function (data) {
            changeLoadingState();
            vm.obj.quantity = 1;
            if($scope.main.ps.quantity[$routeParams.id]>0){
                vm.showAlreadyOrderThisItemErr = true;
            }
        }, function (error) {
            $log.log("Error: ", error);
            changeLoadingState();
        });
        //getAllUsers();
    } else if ($routeParams.groupId) {
        changeLoadingState();
        $http.get('items/getByGroupId/' + $routeParams.groupId, {}).success(function (data) {
            vm.allObj = data;
            changeLoadingState();
        }).error(function (error) {
            $log.log("ERROR: " + error);
            changeLoadingState();
        });
    } else {

        get();
    }


});

