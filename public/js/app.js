"use strict";

var app = angular.module('App', [ 'ngRoute', 'Controllers', 'Services' ]);

app.config([ '$routeProvider', function($routeProvider) {
    $routeProvider.when('/sentences', {
        templateUrl : 'assets/partials/sentence-list.html',
        controller : 'SentenceListCtrl'
    }).otherwise({
        redirectTo : '/sentences'
    });
} ]);