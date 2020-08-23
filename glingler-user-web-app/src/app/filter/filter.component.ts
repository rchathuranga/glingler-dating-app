import {Component, OnInit} from '@angular/core';
import {FilterService} from '../service/filter/filter.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  ageRangeStart: number;
  ageRangeEnd: number;
  interestOn: boolean;

  constructor(private filterService: FilterService, private router: Router) {
  }

  ngOnInit(): void {
  }

  getLocationData(data) {
    if (navigator.geolocation) {
      console.log('it is supported');
      navigator.geolocation.getCurrentPosition(position => {

        const userFilters = {
          ...data,
          ...{longitude: position.coords.longitude},
          latitude: position.coords.latitude
        };

        this.filterService.updateProfileFilter(userFilters).subscribe(
          res => {
            if (res.responseCode === 1000) {
              this.router.navigate(['/application']);
            }
          }, error => {

          });
      });
    } else {
      console.log('geo location not supported');
    }
  }

  btnContinueClick() {
    const data = {
      ageRangeStart: 18,
      ageRangeEnd: 24,
      lookInFor: 'WOMEN'
    };

    this.getLocationData(data);
  }
}
