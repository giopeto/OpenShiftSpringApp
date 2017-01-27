'use strict';

/* PS Controller */

ngApp.lazy.controller('psCtrl', function ($scope, $log, $routeParams, $location, localStorageService, PSFactory) {
    var vm = this;

    vm.isLoading = false;
    vm.errorMsg = {};
    vm.obj = {};
    vm.removeItem = removeItem;
    vm.changeQuantity = changeQuantity;

    function save() {
        PSFactory.update(vm.obj, function () {
            //changeLoadingState();

        }, function (error) {
            $log.log("Error: ", error);
            //changeLoadingState();
        });
    };

    function changeQuantity(args){
        $log.log (args);
        $log.log(vm.obj);
        $log.log(vm.obj.quantity, args.obj.id);
        //vm.obj.quantity
       if(args.tp==="increase"){

            vm.obj.quantity[args.obj.id]++;
        }else{
            vm.obj.quantity[args.obj.id]--;
        }
       if(vm.obj.quantity[args.obj.id].quantity>0) {
            vm.obj.quantity[args.obj.id] = vm.obj.quantity[args.obj.id];
        }
        save();
    }

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

