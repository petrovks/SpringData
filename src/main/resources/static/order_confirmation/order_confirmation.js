angular.module('market-front').controller('orderConfirmationController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8190/webapp/';

    $scope.loadCart = function () {
        $http({
            url: contextPath + 'api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            console.log(response);
            $scope.cart = response.data;
        });
    };

    $scope.loadCart();

    $scope.createOrder = function () {
        $http({
            url: contextPath + 'api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            alert('Ваш заказ успешно сформирован');
            $location.path('/');
        });
    };

});