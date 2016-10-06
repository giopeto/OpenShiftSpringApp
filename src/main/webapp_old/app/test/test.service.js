'use strict';

/* TestFactory Services */

ngApp.lazy.factory('TestFactory', function($resource) {
    var resource = $resource('/test/:id', {id : '@id'}, {
        update: {
            method: 'PUT'
        }
    });

    return resource;
});