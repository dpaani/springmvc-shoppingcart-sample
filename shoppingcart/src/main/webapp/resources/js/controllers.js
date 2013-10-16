'use strict';

/**
 * @constructor
 */

( function () {

    angular.module('ShoppingCart').controller('ShoppingCartViewerCtrl', function ($scope, $rootScope, $http) {
    	$scope.input = null;
    	$scope.purcahseReceipt = null;
    	$scope.shoppingBucket = null;
    	$scope.errorResponse = null;
        $scope.init = function () {
        	var view = (window.location.hash || "");
        	$scope.shoppingBucket = view.charAt(view.length-1);
        	console.log('shoppingBucket :'+$scope.shoppingBucket);
        	$scope.getShoppingBucketInput();
        	$scope.loadShoppingBucket();
        };

        $scope.getShoppingBucketInput = function() {
        	$http({
                url: 'viewbucketinput?bucket='+$scope.shoppingBucket,
                method: "GET",
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
            		$scope.input = data;
            		console.log($scope.input);
                }).error(function (data, status, headers, config) {
                    $scope.errorResponse = data;
                });
        };

        $scope.viewPurchaseReceipt = function() {
        	$http({
                url: 'report/purchase',
                method: "GET",
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
            		$scope.purcahseReceipt = data;
            		console.log($scope.purcahseReceipt);
                }).error(function (data, status, headers, config) {
                    $scope.errorResponse = data;
                });
        };

        $scope.loadShoppingBucket = function() {
        	$http({
                url: 'loadcart/'+$scope.shoppingBucket,
                method: "GET",
                headers: {'Content-Type': 'application/json'}
            }).success(function (data, status, headers, config) {
            		$scope.viewPurchaseReceipt();
                }).error(function (data, status, headers, config) {
                    $scope.errorResponse = data;
                });
        };

        $scope.init();
    });

}());

