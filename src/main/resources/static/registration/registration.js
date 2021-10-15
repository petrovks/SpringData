angular.module('market-front').controller('createUserController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8190/webapp/';

    $scope.createNewUser = function () {
        if ($scope.new_user == null) {
            alert('Не все поля заполнены');
            return;
        }
        $http.post(contextPath + 'api/v1/create_user', $scope.new_user)
            .then(function successCallback (response) {
                $scope.new_user = null;
                alert('Пользователь успешно создан');
                $location.path('/auth');
            }, function failureCallback (response) {
                if (response.data.new_user == null) {
                    alert("Пароли не совпадают");
                    return;
                }
                console.log(response);
                alert(response.data.messages);
            });
    }
});