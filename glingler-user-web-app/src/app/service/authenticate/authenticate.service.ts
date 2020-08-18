import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  constructor(public http: HttpClient) {
  }

  authenticate(user: { username, password }): boolean {
    this.http.post('http://localhost:8080/api/v1/auth/sign-in', user).subscribe(
      res => {
        console.log(res);
        // return true;
      },
      err => {
        console.log(err);
        // return false;
      }
    );
    return true;
    // return (username === 'root' && password === 'ijse');
  }
}
