import { IncomeCalculation } from './incomeCalculation';
import { Injectable } from 'angular2/core';
import { Http, Response, URLSearchParams } from 'angular2/http';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class IncomeCalculationService {
    constructor(private http: Http) {}

    private _incomeCalculationUrl = 'http://localhost:9000/income-calculations';

    getCalculation(dailyRate: number, countryCode: string) {
        var params = new URLSearchParams();
        params.set('daily_rate', dailyRate.toString());
        params.set('country_code', countryCode);
        return this.http.get(this._incomeCalculationUrl, { search: params }).map(res => <IncomeCalculation> res.json())
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        return Observable.throw(error.json().error || 'Server error');
    }
}
