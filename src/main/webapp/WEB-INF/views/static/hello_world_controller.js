var module = angular.module('HelloWorldApp', []);

module.controller('HelloWorldController',
    function ($scope, User) {
        $scope.greeting = "Hello World";
        $scope.someInput = "Some input initial value";
        User.setUsername('jgo');

        $scope.someArray = [
            'asdf',
            'qwer',
            'zxcv',
            '1qaz',
            '2wsx'
        ];

        $scope.reusableListModel = {
            'title': 'tytul listy',
            'items': [
                'item1',
                'item2',
                'item3'
            ]
        };

        $scope.noEloxD = function () {
            $scope.greeting = "No elo xD";
        };

        $scope.noEloXD = function () {
            $scope.greeting = "No elo XD";
        };

        $scope.noEloxP = function () {
            $scope.greeting = "No elo xP";
        };

        $scope.getHelloMessage = function() {
            return User.sayHello();
        }
    }
);

module.filter('pokemon', function() {
    return function(text) {
        var result = '';
        for ( var i = 0; i < text.length; i++) {
            var letter = text[i];
            result += mapLetter(letter, i);
        }
        return result;
    }
});

mapLetter = function(letter, index) {
    if ( index % 2 ) {
        return letter.toLowerCase();
    } else {
        return letter.toUpperCase();
    }
};

module.directive('reusableList', function() {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            list: '='
        },
        template:
        '<div>' +
        '<h2>{{list.title}}</h2>' +
        '<ul><li ng-repeat="item in list.items">{{item}}</li></ul>' +
        '+</div>'
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