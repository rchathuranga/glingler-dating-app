import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-feed',
  templateUrl: './user-feed.component.html',
  styleUrls: ['./user-feed.component.css']
})
export class UserFeedComponent implements OnInit {

  notificationMsg = 'Hello World';

  constructor() {
  }

  ngOnInit(): void {
  }

}
