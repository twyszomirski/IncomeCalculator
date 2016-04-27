import { Country } from './country';
import { Injectable } from 'angular2/core';
import { Http, Response } from 'angular2/http';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class CountryService {
    constructor(private http: Http) {}

    private _countriesUrl = 'http://localhost:9000/countries';

    getCountries(): Observable<Country[]> {
        return this.http.get(this._countriesUrl).map(res => < Country[] > res.json()).catch(this.handleError);
    }
    private handleError(error: Response) {
        return Observable.throw(error.json().error || 'Server error');
    }
}
