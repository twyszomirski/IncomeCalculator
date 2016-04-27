import { it, iit, describe, expect, inject, injectAsync, beforeEachProviders, TestComponentBuilder, fakeAsync, tick } from 'angular2/testing';
import { provide } from 'angular2/core';
import { AppComponent } from '../app/app.component';
import { Country } from '../app/country';
import { CountryService } from '../app/country.service';
import { IncomeCalculationService } from '../app/incomeCalculation.service';
import { Observable } from 'rxjs/Observable';
import { HTTP_PROVIDERS, Http, Response } from 'angular2/http';



class MockCountryService extends CountryService {
    getCountries(): Observable<Country[]> {

        return Observable.create(observer => {
            observer.next([new Country(), new Country()]);
            observer.complete();
        });
    }

}

class MockIncomeCalculationService extends IncomeCalculationService {

}


describe('AppComponent', () => {
    beforeEachProviders(() => [HTTP_PROVIDERS,
        provide(CountryService, { useClass: MockCountryService }),
        provide(IncomeCalculationService, { useClass: MockIncomeCalculationService })
    ]);

    it('should have initiall list of countries', injectAsync([TestComponentBuilder], (tcb) => {
        return tcb.createAsync(AppComponent).then((fixture) => {
            fixture.detectChanges();
            var compiled = fixture.debugElement.nativeElement;


            expect(compiled.querySelector('h3')).toHaveText(' 1. Wybierz kraj ');
        });
    }));

});
