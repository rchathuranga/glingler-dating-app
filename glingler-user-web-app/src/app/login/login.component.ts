import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthenticateService} from '../service/authenticate/authenticate.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form = new FormGroup({
    username: new FormControl(),
    password: new FormControl()
  });

  constructor(private authService: AuthenticateService, private router: Router) {
  }

  ngOnInit(): void {
  }

  login() {
    console.log(this.form.value);
    this.authService.authenticate(this.form.value);
  }
}
