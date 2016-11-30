var app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'pages/home.html',
            controller  : 'mainController'
        })
});

app.controller('mainController', function($scope, ajax) {
    $scope.message = 'Getting users';
    $scope.users;
    
    ajax.getUsers().then(function(response){
    	$scope.users = response;
    });
});