var module = angular.module('App', ["ngResource", "ngRoute", "ngCookies"]);

module.controller('LoginController',
    function ($scope, $http, $httpParamSerializer, $cookies) {

        $scope.error;

        $scope.data = {
            username: '',
            password: '',
            grant_type: 'password'
        };

        $scope.encoded = btoa('clientIdPassword:secret');

        $scope.login = function() {
            console.log('login request for login: ' + $scope.data.username + ', password: ' + $scope.data.password);
            $scope.error = '';
            var request = {
                method: 'POST',
                url: 'http://localhost:8080/app/oauth/token',
                headers: {
                    "Authorization": "Basic " + $scope.encoded,
                    "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                },
                data: $httpParamSerializer($scope.data)
            };

            $http(request).then(
                function (response) {
                    $http.defaults.headers.Authorization = 'Bearer ' + response.data.access_token;
                    $cookies.put("access_token", response.data.acces_token);
                    console.log('received token: ' + response.data.access_token);
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