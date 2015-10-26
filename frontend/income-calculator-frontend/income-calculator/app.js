'use strict';
angular.module('incomeCalculator', ['ngResource'])
    .config(['$httpProvider', function ($httpProvider) {
        //Enable CORS
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }])

    .controller('CalculationCtrl', function ($scope, Country, IncomeCalculation) {

        /**
         * Handles error situation when talking to backend
         */
		$scope.handleError = function(){
			$scope.error = true;
		}

        /**
         * Resets the error indcator
         */
        $scope.resetError = function(){
			$scope.error = false;
		}

        /**
         * Initializes the view (fetches the list of countries)
         */
        $scope.init = function () {
            $scope.formData = {};
            Country.query(function (data) {
                $scope.resetError();
                $scope.countries = data;
                if ($scope.countries.length > 0) {
                    $scope.formData.selectedCountry = $scope.countries[0];
                    $scope.initialized = true;
                }
            }, $scope.handleError);
        }

        /**
         * Updates the result calculation after user changes the country or
         * the daily amount
         */
        $scope.updateCalculation = function () {
            if (!$scope.isDefined($scope.formData.dailyRate)) {
                $scope.resetCalculation();
                return;
            }
            
			IncomeCalculation.get(
                {
                    daily_rate: $scope.formData.dailyRate,
                    country_code: $scope.formData.selectedCountry.countryCode
                },
                function (data) {
                    $scope.resetError();
					$scope.formData.monthlyRate = data.monthlyRate
                    $scope.formData.monthlyTax = data.monthlyTax
                    $scope.formData.additionalCost = data.additionalCost
                }, $scope.handleError);

        };

        /**
         * Set the result fields to initial value (zeros)
         */
        $scope.resetCalculation = function () {
            $scope.formData.monthlyRate = 0;
            $scope.formData.monthlyTax = 0;
            $scope.formData.additionalCost = 0;
        };
		
		/**
		* Checks if a given value is defined
		*/
		$scope.isDefined = function(arg) {
			return typeof (arg) !== 'undefined' && arg !== null;
		};

        $scope.init();
        $scope.resetCalculation();

    })

    .factory("Country", function ($resource) {
        return $resource("http://localhost:9000/countries");
    })

    .factory("IncomeCalculation", function ($resource) {
        return $resource("http://localhost:9000/income-calculations");
    });

