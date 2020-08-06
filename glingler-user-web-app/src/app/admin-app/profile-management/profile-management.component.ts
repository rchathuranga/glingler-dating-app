import {Component, OnInit} from '@angular/core';
import {Profile} from '../../dto/profile';

@Component({
  selector: 'app-profile-management',
  templateUrl: './profile-management.component.html',
  styleUrls: ['./profile-management.component.css']
})
export class ProfileManagementComponent implements OnInit {

  profiles: Profile[] = [];

  constructor() {
  }

  ngOnInit(): void {
    const prof: Profile = new Profile(1, 'Ravindu', 'Chathuranga', '', 'Male',
      19, '2001-11-12', '', '12:12:43', 34, 'ACT');

    this.profiles.push(prof);
    this.profiles.push(prof);
    this.profiles.push(prof);
  }


}
