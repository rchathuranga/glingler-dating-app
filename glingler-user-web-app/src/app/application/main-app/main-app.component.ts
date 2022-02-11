import {Component, OnInit} from '@angular/core';
import {Observable, Observer} from 'rxjs';
import {AuthenticateService} from '../../service/authenticate/authenticate.service';
import {ProfileService} from '../../service/profile/profile.service';
import {MatDialog} from '@angular/material/dialog';
import {SmAlertDialogComponent} from '../../shared/components/sm-alert-dialog/sm-alert-dialog.component';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SmAlertComponent} from '../../shared/components/sm-alert/sm-alert.component';
import {fader} from '../route.animation';
import {Route, RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-main-app',
  templateUrl: './main-app.component.html',
  styleUrls: ['./main-app.component.css'],
  animations: [
    fader
  ]
})
export class MainAppComponent implements OnInit {

  private durationInSeconds = 5;

  constructor(private authService: AuthenticateService,
              private profileService: ProfileService,
              public snackBar: MatSnackBar) {
    const profileId = +(localStorage.getItem('profileId'));
    profileService.getFirebaseDBRef(profileId).set('ACTIVE');
  }

  ngOnInit(): void {
    // this.openDialog();
  }

  openDialog() {
    const snake = this.snackBar.open('Success', 'Close',
      {duration: this.durationInSeconds * 1000});
    console.log(snake);
  }

  logout() {
    const profileId = +(localStorage.getItem('profileId'));
    this.profileService.getFirebaseDBRef(profileId).set('DEACT');
    this.authService.logout();
  }

  prepareRoute(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData.animation;
  }
}
