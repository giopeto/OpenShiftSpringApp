'use strict';

/* UsersFactory Services */

ngApp.lazy.factory('UserFactory', function($resource) {
    var resource = $resource('users/:id', {id : '@id'}, {
        update: {
            method: 'PUT'
        }
    });

    return resource;
});
