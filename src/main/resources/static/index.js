angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/webapp/';

    $scope.loadProducts = function () {
        $http.get(contextPath + 'products')
            .then(function (response) {
                console.log(response);
                $scope.products = response.data;
            });
    };

    // $scope.loadProducts = function (pageIndex = 1) {
    //     $http({
    //         url: contextPath + 'products',
    //         method: 'GET',
    //         params: {
    //             p: pageIndex
    //         }
    //     }).then(function (response) {
    //         console.log(response);
    //         $scope.productsPage = response.data;
    //     });
    // };

    $scope.showInfo = function (product) {
        alert('Title: ' + product.title + '\n' + 'Price: ' + product.price);
    };

    $scope.dec = function (product) {
        $http.get(contextPath + 'product/' + product.id + '/decrementCost')
            .then(function (response) {
                console.log(response);
                $scope.product = response.data;
                $scope.loadProducts();
            });
    };

    $scope.inc = function (product) {
        $http.get(contextPath + 'product/' + product.id + '/incrementCost')
            .then(function (response) {
                console.log(response);
                $scope.product = response.data;
                $scope.loadProducts();
            });
    };

    $scope.delete = function (product) {
        $http.get(contextPath + 'delete/product/' + product.id)
            .then(function (response) {
                console.log(response);
                $scope.product = response.data;
                $scope.loadProducts();
            });
    }

    // $scope.wrongRequest = function () {
    // WRONG:
    // $http.get(contextPath + 'products/update/1');
    // reload();

    // CORRECT
    // $http.get(contextPath + 'products/update/1')
    //     .then(function (response) {
    //         reload();
    //     });
    // }

    $scope.loadProducts();
});
