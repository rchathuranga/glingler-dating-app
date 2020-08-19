import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ResponseDTO} from '../../dto/response-dto';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';
import {UserDTO} from '../../dto/user-dto';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  TOKEN_KEY = 'TOKEN';
  baseUrl = environment.glingler_api_base_url;
  private USER_DATA = 'USER';

  constructor(private http: HttpClient, private router: Router) {
  }

  authenticate(user: { username, password }) {
    this.http.post<ResponseDTO>(this.baseUrl + 'auth/sign-in', user).subscribe(
      res => {
        this.http.get<ResponseDTO>(this.baseUrl + 'user//user/user').subscribe(
          gRes => {
            localStorage.setItem(this.TOKEN_KEY, res.token);
            console.log(gRes);
            if (gRes.data.length > 0) {
              const userData: UserDTO = gRes.data[0];
              localStorage.setItem(this.USER_DATA, userData.status);
              this.router.navigate(['/application']);
            }
          },
          gErr => {
            console.log(gErr);
            this.logout();
          }
        );
        this.router.navigate(['/admin']);
      },
      err => {
        console.log(err);
        this.logout();
      }
    );
    // return (username === 'root' && password === 'ijse');
  }

  isUserLogged(): boolean {
    return !!localStorage.getItem(this.TOKEN_KEY);
  }

  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_DATA);
    this.router.navigate(['/login']);
  }
}
