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
  asyncTabs: Observable<ExampleTab[]>;

  constructor(private authService: AuthenticateService, private profileService: ProfileService) {

    this.asyncTabs = new Observable((observer: Observer<ExampleTab[]>) => {
      setTimeout(() => {
        observer.next([
          {label: 'First', content: 'Content 1'},
          {label: 'Second', content: 'Content 2'},
          {label: 'Third', content: 'Content 3'},
        ]);
      }, 1000);
    });
  }

  ngOnInit(): void {
  }

  logout() {
    const profileId = +(localStorage.getItem('profileId'));
    this.profileService.getFirebaseDBRef(profileId).remove();
    this.authService.logout();
  }
}
