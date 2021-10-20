angular.module('market-front').controller('storeController', function ($rootScope, $scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8190/webapp/';
    let currentPageIndex = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        currentPageIndex = pageIndex;
        $http({
            url: contextPath + 'api/v1/products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, $scope.productsPage.totalPages);
        });
    };

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    };

    $scope.navToEditProductPage = function (productId) {
        $location.path('/edit_product/' + productId);
    };

    $scope.showInfo = function (product) {
        alert('Title: ' + product.title + '\n' + 'Price: ' + product.price);
    };

    $scope.dec = function (product) {
        $http.get(contextPath + 'api/v1/products/' + product.id + '/decrementCost')
            .then(function (response) {
                console.log(response);
                $scope.product = response.data;
                $scope.loadProducts();
            });
    };

    $scope.inc = function (product) {
        $http.get(contextPath + 'api/v1/products/' + product.id + '/incrementCost')
            .then(function (response) {
                console.log(response);
                $scope.product = response.data;
                $scope.loadProducts();
            });
    };

    $scope.addProductToCart = function (product) {
        $http.get(contextPath + 'api/v1/cart/add/' + product.id)
            .then(function (response) {
                console.log(response);
                alert('Продукт успешно добавлен в корзину');
                $scope.product = response.data;
            });
    };

    $scope.delete = function (product) {
        $http.get(contextPath + 'api/v1/products/delete/' + product.id)
            .then(function (response) {
                console.log(response);
                $scope.product = response.data;
                $scope.loadProducts();
            });
    };

    $rootScope.isAdminLoggedIn = function () {
        if ($localStorage.webMarketUser.username == 'admin') {
            return true;
        } else {
            return false;
        }
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.webMarketUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.loadProducts();
});