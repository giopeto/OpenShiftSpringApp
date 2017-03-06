'use strict';

/* FilesFactory Services */

ngApp.factory('FilesFactory', function ($resource) {
    var resource = $resource('files/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        }
    });

    return resource;
});