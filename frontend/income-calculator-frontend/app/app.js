'use strict';
angular.module('incomeCalculator', ['ngResource'])
    .config(['$httpProvider', function ($httpProvider) {
        //Enable CORS
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }])

    .controller('CalculationCtrl', function ($scope, Country, IncomeCalculation) {
        $scope.formData = {};
        Country.query(function (data) {
            $scope.countries = data;
            if ($scope.countries.length > 0) {
                $scope.formData.selectedCountry = $scope.countries[0];
            }
        });

        $scope.updateCalculation = function () {
            IncomeCalculation.calculate(
                {
                    dailyRate: $scope.formData.dailyRate,
                    countryCode: $scope.formData.selectedCountry.countryCode
                },
                function (data) {
                    $scope.formData.monthlyRate = data.monthlyRate
                    $scope.formData.monthlyTax = data.monthlyTax
                    $scope.formData.additionalCost = data.additionalCost
                });
        }
    })

    .factory("Country", function ($resource) {
        return $resource("http://localhost:9000/countries");
    })

    .factory("IncomeCalculation", function ($resource) {
        return $resource("http://localhost:9000/incomeCalculations", {},
            {calculate: {method: 'POST', isArray: false}});
    });

