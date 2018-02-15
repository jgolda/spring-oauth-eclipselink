var module = angular.module('app');

module.controller('LoginController',
    function ($scope, $route, $location, $http, $httpParamSerializer, $cookies) {

        $scope.error = '';

        $scope.data = {
            username: '',
            password: '',
            client_id: 'jsclient',
            client_secret: 'jsclient',
            grant_type: 'password'
        };

        $scope.login = function() {
            console.log('login request for login: ' + $scope.data.username + ', password: ' + $scope.data.password);
            $scope.error = '';
            var request = {
                method: 'POST',
                url: 'http://localhost:8080/app/oauth/token',
                headers: {
                    "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data: $httpParamSerializer($scope.data)
            };

            $http(request).then(
                function (response) {
                    $http.defaults.headers.Authorization = 'Bearer ' + response.data.access_token;
                    $cookies.put("access_token", response.data.acces_token);
                    console.log('received token: ' + response.data.access_token);
                    $location.url('/private')
                },
                function (response) {
                    $scope.error = response.data.error_description;
                }
            );
            $scope.data.username = '';
            $scope.data.password = '';
        }
    }
);