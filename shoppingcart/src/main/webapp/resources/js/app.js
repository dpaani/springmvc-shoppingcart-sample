'use strict';

var ShoppingCart = {};

var App = angular.module('ShoppingCart', []);

App.config(function($locationProvider, $routeProvider) {
	$routeProvider
		.when('/shoppingcartviewer1', {
	        templateUrl: 'resources/partials/shoppingcartviewer.html',
	        controller: 'ShoppingCartViewerCtrl'
	    })
		.when('/shoppingcartviewer2', {
	        templateUrl: 'resources/partials/shoppingcartviewer.html',
	        controller: 'ShoppingCartViewerCtrl'
	    })
		.when('/shoppingcartviewer3', {
	        templateUrl: 'resources/partials/shoppingcartviewer.html',
	        controller: 'ShoppingCartViewerCtrl'
	    });


	$routeProvider.otherwise({redirectTo: '/shoppingcartviewer1'});
});

App.run(function($rootScope, $location){
	  $rootScope.menuActive = function(url, exactMatch){
	  	//alert(url+" "+exactMatch);
	    if (exactMatch){
	      return $location.path() == url;
	    }
	    else {
	      return $location.path().indexOf(url) == 0;
	    }
	  };
	});


