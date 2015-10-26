'use strict';

describe('incomeCalculator module', function () {
    beforeEach(module('incomeCalculator'));

    describe('CalculationCtrl controller', function () {

        it('should initially fetch list of possible countries', inject(function (_$httpBackend_, $rootScope, $controller) {
            var scope = $rootScope.$new();
            var mockedHttp = _$httpBackend_;
            mockedHttp.expectGET('http://localhost:9000/countries').respond([{name: 'Poland'}, {name: 'Boland'}]);
            var calculationCtrl = $controller('CalculationCtrl', {$scope: scope});
            expect(calculationCtrl).toBeDefined();
            expect(scope.formData).toBeDefined();
            expect(scope.countries).toBeUndefined();

            mockedHttp.flush();

            expect(scope.countries).toBeDefined();
            expect(scope.countries.length).toEqual(2);
            expect(scope.countries[0].name).toEqual('Poland');
            expect(scope.countries[1].name).toEqual('Boland');

        }));

        it('be able to update the calculation', inject(function (_$httpBackend_, $rootScope, $controller) {
            var scope = $rootScope.$new();
            var mockedHttp = _$httpBackend_;
            mockedHttp.expectGET('http://localhost:9000/countries').respond([{name: 'Poland'}, {name: 'Boland'}]);
            mockedHttp.expectGET('http://localhost:9000/income-calculations?country_code=PL&daily_rate=1').respond({
                monthlyRate: 1.0,
                monthlyTax: 2.0,
                additionalCost: 3
            });
            var calculationCtrl = $controller('CalculationCtrl', {$scope: scope});
            scope.formData.dailyRate = 1.0;
            scope.formData.selectedCountry = {countryCode: 'PL'};

            expect(scope.formData.monthlyRate).toEqual(0);
            expect(scope.formData.monthlyTax).toEqual(0);
            expect(scope.formData.additionalCost).toEqual(0);

            scope.updateCalculation();
            mockedHttp.flush();

            expect(scope.formData.monthlyRate).toEqual(1.0);
            expect(scope.formData.monthlyTax).toEqual(2.0);
            expect(scope.formData.additionalCost).toEqual(3);

        }));

    });
});