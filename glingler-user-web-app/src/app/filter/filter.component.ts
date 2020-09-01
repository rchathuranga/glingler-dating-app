import {Component, OnInit, ViewChild} from '@angular/core';
import {FilterService} from '../service/filter/filter.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  @ViewChild('ageStart') ageRangeStart;
  @ViewChild('ageEnd') ageRangeEnd;
  isInterestOnMen = true;

  constructor(private filterService: FilterService, private router: Router) {
  }

  ngOnInit(): void {
  }

  getLocationData(data) {
    // if (navigator.geolocation) {
    //   console.log('it is supported');
    //   navigator.geolocation.getCurrentPosition(position => {
    //
    //     const userFilters = {
    //       ...data,
    //       ...{longitude: position.coords.longitude},
    //       latitude: position.coords.latitude
    //     };
    //
    //
    //   });
    // } else {
    //   console.log('geo location not supported');
    // }


    this.filterService.updateProfileFilter(data).subscribe(
      res => {
        if (res.responseCode === 1000) {
          this.router.navigate(['/application']);
        }
      }, error => {

      });
  }

  btnContinueClick() {
    const data = {
      ageRangeStart: this.ageRangeStart.nativeElement.value,
      ageRangeEnd: this.ageRangeEnd.nativeElement.value,
      lookInFor: (this.isInterestOnMen) ? 'MEN' : 'WOMEN'
    };
    console.log(data);
    this.getLocationData(data);
  }

  interestFemaleKeyPress($event: KeyboardEvent) {
    this.isInterestOnMen = false;
  }

  interestMaleKeyPress($event: KeyboardEvent) {
    this.isInterestOnMen = true;
  }
}
