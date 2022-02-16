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
  private profileId: number;
  constructor(private authService: AuthenticateService,
              private profileService: ProfileService,
              public snackBar: MatSnackBar) {
    this.profileId = +(localStorage.getItem('profileId'));
    this.fb().then(value => {});
  }

  public async fb() {
    let response;
    await this.profileService.getFirebaseDBRef(this.profileId).snapshotChanges().subscribe(resp => {
      response = resp.payload.toJSON();
    });
    if (response !== undefined) {
      await this.profileService.getFirebaseDBRef(this.profileId).set({status: 'ACTIVE', LIA_TIME: response['LIA_TIME']});
    }
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
    this.profileService.getFirebaseDBRef(profileId).snapshotChanges().subscribe(resp => {
      const response = resp.payload.toJSON();
      if (response['status'] === 'ACTIVE') {
        this.profileService.getFirebaseDBRef(profileId).set({status: 'DEACT', LIA_TIME: new Date().getTime()});
      }
    });
    // this.profileService.getFirebaseDBRef(profileId).set({
    //   status: 'DEACT',
    //   LIA_TIME: new Date().getTime(),
    // });
    this.authService.logout();
  }

  prepareRoute(outlet: RouterOutlet) {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData.animation;
  }
}
