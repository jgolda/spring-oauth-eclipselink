var module = angular.module('app', ['ngRoute', 'ngCookies'])
    .config(function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/public', {
                controller: 'LoginController',
                templateUrl: 'static/views/login.view.html'
            })
            .when('/private', {
                controller: 'HomeController',
                templateUrl: 'static/views/home.view.html'
            })
            .otherwise({redirectTo: '/public'});
    });