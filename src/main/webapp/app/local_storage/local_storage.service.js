'use strict';

/* Local Storage Services */

ngApp.service('localStorageService', function($log) {

	var IS_LOCAL_STORAGE_SUPPORTED = typeof(Storage)!=="undefined";

	this.localStorageCheck = function() {
		if(!IS_LOCAL_STORAGE_SUPPORTED) {
			$log.log("Your browser does not support web storage");
		}
		return IS_LOCAL_STORAGE_SUPPORTED;
	};

	this.set = function(key, args) {
		if(this.localStorageCheck()) {
			localStorage.setItem(key, JSON.stringify(args));
		}
	};

	this.get = function(key) {
		if(this.localStorageCheck()) {
			var localStorageItem = localStorage.getItem(key);
			return localStorageItem ? JSON.parse(localStorageItem) : {};
		}
	}

	this.remove = function(key) {
		if(this.localStorageCheck()) {
			$log.log ("Remove");
			localStorage.removeItem(key);
		}
	}

});