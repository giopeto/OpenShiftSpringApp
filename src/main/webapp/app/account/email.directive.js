ngApp.lazy.directive('ossaEmail',function(){

    function checkEmail (value, element, ngModelCtrl){
        var isValidMail = true;
        if (!value) {
            element.css("border", "1px solid #ccc");
            return;
        }
        isValidMail = ngModelCtrl.$isEmpty(value) || (angular.isString(value) ? value : value.toString()).match(/^([a-zA-Z0-9_\.\,\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/);
        if (isValidMail === null) {
            isValidMail = false;
        }else{
            isValidMail = true;
        }
        if(isValidMail){
            element.css("border-color", "#5cb85c");
        }else{
            element.css("border-color", "#d9534f");
        }
    }

    return {
        restrict:'A',
        require:'ngModel',
        link:function(scope, element, attrs, ngModelCtrl){
            scope.$watch(attrs.ngModel, function (v) {
                checkEmail(v, element, ngModelCtrl);
            });
        }
    };

});