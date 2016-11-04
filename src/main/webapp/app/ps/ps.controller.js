'use strict';

/* PS Controller */

ngApp.lazy.controller('psCtrl', function ($scope, $log, $routeParams, $location, localStorageService, PSFactory) {
    var vm = this;

    vm.isLoading = false;
    vm.errorMsg = {};
    vm.obj = {};
    vm.removeItem = removeItem;


    function save() {
        PSFactory.update(vm.obj, function () {
            //changeLoadingState();
            alert("Success")
        }, function (error) {
            $log.log("Error: ", error);
            //changeLoadingState();
        });
    };

    function removeItem(args) {
        delete vm.obj.quantity[args.id];
        vm.obj.items.splice(args.index, 1);
        save();
    };

    $log.log($routeParams);
    if ($routeParams.id) {
        vm.obj = PSFactory.query({id: $routeParams.id});
    } else {
        //get();
    }


});

