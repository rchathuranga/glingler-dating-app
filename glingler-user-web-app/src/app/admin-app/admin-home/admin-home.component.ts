import {Component, OnInit, ViewChild} from '@angular/core';
import {MatDrawer} from '@angular/material/sidenav';
import {Router} from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  @ViewChild('sidenav')
  navBar: MatDrawer;

  count = 12;

  adminContentList: any = [
    {
      icon: 'dashboard',
      desc: 'Dashboard',
      urlPath: 'dashboard'
    },
    {
      icon: 'explore',
      desc: 'Real-Time Stat',
      urlPath: 'hello'
    },
    {
      icon: 'supervised_user_circle',
      desc: 'Profiles',
      urlPath: 'profile'
    },
    {
      icon: 'bubble_chart',
      desc: 'User Activities',
      urlPath: 'analytic'
    },
    {
      icon: 'favorite',
      desc: 'Analytics',
      urlPath: 'qewr'
    }
  ];

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }



  notClick() {
    this.count++;
  }
}

