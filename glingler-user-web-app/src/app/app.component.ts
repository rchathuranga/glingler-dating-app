import {Component, OnDestroy, OnInit} from '@angular/core';
import {PushService} from './service/push/push.service';
import {BehaviorSubject} from 'rxjs';
import {ProfileService} from './service/profile/profile.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  title: BehaviorSubject<null> | string = 'glingler-user-web-app';

  constructor(private pushService: PushService, private profileService: ProfileService) {
    this.pushService.requestPermission();
    this.pushService.receiveMessage();
    this.title = this.pushService.currentMessage;
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    const profileId = +(localStorage.getItem('profileId'));
    this.profileService.getFirebaseDBRef(profileId).set('DEACT');
  }

}
