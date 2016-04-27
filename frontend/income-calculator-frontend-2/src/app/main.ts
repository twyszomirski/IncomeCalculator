import { bootstrap } from 'angular2/platform/browser'
import { AppComponent } from './app.component'
import 'rxjs/Rx'
import { HTTP_PROVIDERS } from 'angular2/http';
import { CountryService } from './country.service';
import { IncomeCalculationService } from './incomeCalculation.service';

bootstrap(AppComponent, [
    CountryService,
    IncomeCalculationService,
    HTTP_PROVIDERS
]);
