angular.module('market-front').controller('statController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8190/webapp/';

    $scope.stat = function() {
        $http.get(contextPath + 'api/v1/stat')
            .then(function (response) {
                $scope.statistic = response.data;
                console.log(response);
            });
    };

    $scope.stat();
});