"use strict";

var services = angular.module('Services', ['ngResource']);

services.factory('Sentence', ['$resource',
  function($resource){
    return $resource('sentence?since=0', {}, {
      query: {method:'GET', params:{}, isArray:true}
    });
  }]);