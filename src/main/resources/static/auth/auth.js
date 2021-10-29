angular.module('market-front').controller('authController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8190/webapp';

    $scope.tryToAuth = function() {
        $http.post(contextPath + '/auth_log', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.webMarketUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;
                    $http.get(contextPath + '/api/v1/cart/' + $localStorage.webMarketGuestCartId + '/merge')
                        .then(function successCallback(response) {
                        });
                    $location.path('/store');
                }
            }, function errorCallback(response) {

            });
    };

    $scope.NavToRegistration = function () {
        $location.path("/registration");
    }

});