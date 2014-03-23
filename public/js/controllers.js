"use strict";

var controllers = angular.module('Controllers', []);

controllers.controller('SentenceListCtrl', [ '$scope', 'Sentence', function($scope, Sentence) {
    $scope.sentences = Sentence.query();
} ]);