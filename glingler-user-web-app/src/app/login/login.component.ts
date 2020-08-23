import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthenticateService} from '../service/authenticate/authenticate.service';
import {Router} from '@angular/router';
import {environment} from '../../environments/environment';

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
    if (this.form.value.username === 'admin') {
      this.router.navigate(['admin']);
    }else {
      this.authService.authenticate(this.form.value).subscribe(
        res => {
          localStorage.setItem(environment.glingler_token_key, res.token);
          this.router.navigate(['/' + res.router]);
        },
        err => {
          console.log(err);
          this.authService.logout();
        }
      );
    }
  }
}
