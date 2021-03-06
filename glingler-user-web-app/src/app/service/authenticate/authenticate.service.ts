import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ResponseDTO} from '../../dto/response-dto';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  baseUrl = environment.glingler_api_base_url;
  TOKEN_KEY = environment.glingler_token_key;
  USER_DATA = environment.glingler_user_data;

  constructor(private http: HttpClient, private router: Router) {
  }

  authenticate(user: { username, password }) {
    return this.http.post<ResponseDTO>(this.baseUrl + 'auth/sign-in', user);
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
