angular.module('market-front').controller('cartController', function ($scope, $http) {
    const contextPath = 'http://localhost:8190/webapp/';

    $scope.refreshCart = function () {
        $http.get(contextPath + 'api/v1/cart')
            .then(function (response) {
            console.log(response);
            $scope.products = response.data;
        });
    };

    $scope.deleteFromCart = function (product) {
        $http.get(contextPath + 'api/v1/cart/delete/' + product.id)
            .then(function (response) {
                console.log(response);
                $scope.product = response.data;
                $scope.refreshCart();
            });
    }

    $scope.deleteAllFromCart = function () {
        $http.get(contextPath + 'api/v1/cart/delete/all')
            .then(function (response) {
                console.log(response);
                $scope.products = response.data;
                $scope.refreshCart();
            });
    }



    $scope.refreshCart();
});