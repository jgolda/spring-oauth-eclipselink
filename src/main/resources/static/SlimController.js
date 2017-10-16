var module = angular.module('HelloWorldApp', []);

module.controller('SlimController', function($scope, User) {
    User.setUsername('jgo');

    $scope.getHelloMessage = function() {
        return User.getHelloMessage();
    }
});


module.factory('User', function() {
    var _username = '';

    _setUsername = function(username) {
        _username = username;
    };

    _sayHello = function() {
        return 'Hello, ' + _username;
    };

    return {
        setUsername: _setUsername,
        sayHello: _sayHello
    };
});