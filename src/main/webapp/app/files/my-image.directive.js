ngApp.directive('myImage', function ($http, $log) {
    //Not used for now
    return {
        restrict: 'E',
        replace: true,
        scope: {
            myImageArgs: "="
        },
        template: function (scope, element, attrs) {
            return '<img style="width: 50%" src="{{src}}" class="img-fluid center-block"></div>';
        },
        link: function (scope, element, attrs) {
            scope.src = '/files/' + scope.myImageArgs.fileId;
        }
    }
});