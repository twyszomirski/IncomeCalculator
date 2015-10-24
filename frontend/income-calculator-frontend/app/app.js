'use strict';
angular.module('incomeCalculator', ['ngResource'])
    .config(['$httpProvider', function ($httpProvider) {
        //Enable CORS
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }])

    .controller('CalculationCtrl', function ($scope, Country, IncomeCalculationService) {
        $scope.formData = {};
        Country.query(function (data) {
            $scope.countries = data;
            if ($scope.countries.length > 0) {
                $scope.formData.selectedCountry = $scope.countries[0];
            }
        });

        $scope.updateCalculation = function () {
            IncomeCalculationService.calculate($scope.formData).then(
                function (response) {
                    $scope.formData.monthlyRate = response.data.monthlyRate
                    $scope.formData.monthlyTax = response.data.monthlyTax
                    $scope.formData.additionalCost = response.data.additionalCost
                });
        }
    })
    //TODO: Decide on how to talk with the backend
    .factory("Country", function ($resource) {
        return $resource("http://localhost:9000/countries");
    })

    .service("IncomeCalculationService", function ($http, $q) {
        return ({
            calculate: calculate
        })

        function calculate(postData) {
            return $http({
                method: "post",
                url: "http://localhost:9000/incomeCalculations",
                data: {
                    dailyRate: postData.dailyRate,
                    countryCode: postData.selectedCountry.countryCode
                }
            });
        }

    });
