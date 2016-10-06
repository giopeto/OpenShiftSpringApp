'use strict';

/* Test Controller */

ngApp.lazy.controller('testCtrl', function($scope, $log, $location, $routeParams, TestFactory) {
    var vm = this;
    vm.isLoading = false;
    vm.allObj = [];

    vm.save = save;
    vm.get = get;
    vm.update = update;
    vm.addEdit = addEdit;
    vm.remove = remove;
    vm.goBack = goBack;

    function save () {
        changeLoadingState();
        TestFactory.save(vm.obj, function (data) {
            goBack();
        }, function (error) {
            $log.log("Error: ", error);
            changeLoadingState();
        });
    };

    function get () {
        changeLoadingState();
        vm.allObj = TestFactory.query({}, function() {
            changeLoadingState();
        }, function (error) {
            $log.log ("Error: ", error);
            changeLoadingState();
        });
    };

    function update () {
        changeLoadingState();
        vm.obj.id = vm.obj._id;
        TestFactory.update(vm.obj, function (data) {
            goBack();
        }, function (error) {
            $log.log("Error: ", error);
            changeLoadingState();
        });
    };

    function remove (args) {
        changeLoadingState();
        TestFactory.delete({id: args._id}, function (data) {
            vm.allObj.splice(args.index, 1);
            changeLoadingState();
        }, function (error) {
            $log.log ("Error: ", error);
            changeLoadingState();
        });
    };

    function addEdit (args){
        var id = args._id ? args._id : 0;
        $location.path('/test_add_edit/'+id);
    };

    function goBack () {
        $location.path('/test');
    };

    function changeLoadingState(){
        vm.isLoading = !vm.isLoading;
    };

    if ($routeParams.id && $routeParams.id != 0) {
        changeLoadingState();
        vm.obj = TestFactory.get({ id: $routeParams.id }, function (data) {
            changeLoadingState();
        }, function (error) {
            $log.log ("Error: ", error);
            changeLoadingState();
        });
    } else {
        get();
    }

});