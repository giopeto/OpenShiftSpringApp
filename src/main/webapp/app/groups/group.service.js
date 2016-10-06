'use strict';

/* GroupFactory Services */

ngApp.factory('GroupFactory', function($resource) {
	var resource = $resource('groups/:id', {id : '@id'}, {
		update: {
			method: 'PUT'
		}
	});

	return resource;
});