angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8190/webapp/';

    $scope.refreshCart = function () {
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId,
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.removeItem = function (productId) {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId + '/remove/' + productId)
            .then(function (response) {
                $scope.product = response.data;
                $scope.refreshCart();
            });
    }

    $scope.incrementItem = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId +'/increment/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.refreshCart();
        });
    };

    $scope.decrementItem = function (productId) {
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId + '/decrement/' + productId,
            method: 'GET'
        }).then(function (response) {
            $scope.refreshCart();
        });
    };

    $scope.deleteAllFromCart = function () {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.webMarketGuestCartId + '/remove/all')
            .then(function (response) {
                console.log(response);
                $scope.products = response.data;
                $scope.refreshCart();
            });
    }

    $scope.checkOut = function () {
        $location.path("/order_confirmation");
    }

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.refreshCart();
});