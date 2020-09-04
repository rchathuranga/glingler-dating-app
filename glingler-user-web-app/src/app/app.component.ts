import {Component, OnInit} from '@angular/core';
import {PushService} from './service/push/push.service';
import {BehaviorSubject} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: BehaviorSubject<null> | string = 'glingler-user-web-app';

  constructor(private pushService: PushService) {
    this.pushService.requestPermission();
    this.pushService.receiveMessage();
    this.title = this.pushService.currentMessage;

    console.log('title');
    console.log(this.title);
  }

  ngOnInit(): void {
    console.log('title');
    console.log(this.title);
  }
}
