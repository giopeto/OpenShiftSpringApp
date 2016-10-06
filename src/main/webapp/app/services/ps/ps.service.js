'use strict';

/* PSFactory Services */

ngApp.factory('PSFactory', function($resource) {
	var resource = $resource('ps/:id', {id : '@id'}, {
		update: {
			method: 'PUT'
		},
		query: {
			isArray: false
		},
	});

	return resource;
});
