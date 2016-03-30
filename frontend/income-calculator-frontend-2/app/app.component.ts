import {Country} from'./country';
import {Component, OnInit}from 'angular2/core';
import {HTTP_PROVIDERS}from 'angular2/http';
import {CountryService}from './country.service';
import {IncomeCalculationService}       from './incomeCalculation.service';


@Component({
	selector: 'my-app',
	templateUrl: './view.html',	
	providers: [
	CountryService,
	IncomeCalculationService,
	HTTP_PROVIDERS
]


})
export class AppComponent implements OnInit{
	constructor(
	private _countryService: CountryService, private _incomeService: IncomeCalculationService){}
	
	countries : Country[];
	selectedCountry : Country;
	dailyRate : number;
	monthlyRate : number;
	monthlyTax : number ;
	additionalCost :number;
	
	ngOnInit() {
		this.selectedCountry = new Country();
		this.monthlyRate = 0;
		this.monthlyTax = 0;
		this.additionalCost = 0;
		this._countryService.getCountries()
			.subscribe(
				countries => (this.countries = countries, this.selectedCountry = this.countries[0]),
				error =>  console.log('error'));
}

	onSelect(conutry: Country) { this.selectedCountry = conutry }

	doCalc(dailyRate : number){
			this._incomeService.getCalculation(dailyRate,this.selectedCountry.countryCode)
				.subscribe(
					calculation => (this.monthlyRate = calculation.monthlyRate,this.monthlyTax = calculation.monthlyTax),
					error =>  console.log('error'));
	}

}





