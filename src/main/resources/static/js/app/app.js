var app = angular.module('stockApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/StocksApp',
    STOCK_SERVICE_API : 'http://localhost:8080/StocksApp/api/stock/'
});

app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: 'partials/list',
                controller:'StockController',
                controllerAs:'ctrl',
                resolve: {
                    stock: function ($q, StockService) {
                        console.log('Load all stocks');
                        var deferred = $q.defer();
                        StockService.loadAllStocks().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

