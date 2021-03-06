'use strict';

/* Items Controller */

ngApp.lazy.controller('filesCtrl', function ($rootScope, $scope, $http, $log) {
    var vm = this;

    vm.uploadFile = uploadFile;

    function uploadFile(args) {
        var uploadUrl = "/files";
        var file = $scope.myFile;

        var fd = new FormData();
        fd.append('file', file);
        $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            })
            .success(function (data) {
                args.fileId = data.id;
                $rootScope.$emit("filesChanged", args);
            })
            .error(function (error) {
                $log.log("Error: ", error);
            });
    };
});

