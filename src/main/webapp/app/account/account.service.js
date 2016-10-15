'use strict';

/* AccountFactory Services */

ngApp.lazy.factory('AccountFactory', function($resource) {
	var resource = $resource('accounts/:id', {id : '@id'}, {
		update: {
			method: 'PUT'
		}
	});

	return resource;
});