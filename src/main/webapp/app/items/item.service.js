'use strict';

/* ItemFactory Services */

ngApp.lazy.factory('ItemFactory', function ($resource) {
    var resource = $resource('items/:id', {id: '@id'}, {
        update: {
            method: 'PUT'
        }
    });

    return resource;
});
