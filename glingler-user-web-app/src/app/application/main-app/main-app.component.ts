import {Component, OnInit} from '@angular/core';
import {Observable, Observer} from 'rxjs';
import {AuthenticateService} from '../../service/authenticate/authenticate.service';
import {ProfileService} from '../../service/profile/profile.service';

export interface ExampleTab {
  label: string;
  content: string;
}

@Component({
  selector: 'app-main-app',
  templateUrl: './main-app.component.html',
  styleUrls: ['./main-app.component.css']
})
export class MainAppComponent implements OnInit {

  constructor(private authService: AuthenticateService, private profileService: ProfileService) {
    const profileId = +(localStorage.getItem('profileId'));
    profileService.getFirebaseDBRef(profileId).set('ACTIVE');
  }

  ngOnInit(): void {
  }

  logout() {
    const profileId = +(localStorage.getItem('profileId'));
    this.profileService.getFirebaseDBRef(profileId).set('DEACT');
    this.authService.logout();
  }
}
