angular.module('market-front', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/webapp/';
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

    $scope.delete = function (product) {
        $http.get(contextPath + 'api/v1/products/delete/' + product.id)
            .then(function (response) {
                console.log(response);
                $scope.product = response.data;
                $scope.loadProducts();
            });
    }

    $scope.createNewProduct = function () {
        $http.post(contextPath + 'api/v1/products', $scope.new_product)
            .then(function successCallback (response) {
                $scope.loadProducts(currentPageIndex);
                $scope.new_product = null;
            }, function failureCallback (response) {
                alert(response.data.message);
            });
    }

    $scope.updateProduct = function () {
        $http.put(contextPath + 'api/v1/products', $scope.update_product)
            .then(function successCallback (response) {
                $scope.loadProducts(currentPageIndex);
                $scope.update_product = null;
            }, function failureCallback (response) {
                alert(response.data.message);
            });
    }

    $scope.prepareForUpdate = function (productId) {
        $http.get(contextPath + 'api/v1/products/' + productId)
            .then(function successCallback (response) {
                $scope.update_product = response.data;
            }, function failureCallback (response) {
                alert(response.data.message);
            });
    }



    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
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
