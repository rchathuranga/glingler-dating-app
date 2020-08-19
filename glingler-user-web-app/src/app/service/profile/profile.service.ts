import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ResponseDTO} from '../../dto/response-dto';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  baseUrl = environment.glingler_api_base_url;

  constructor(private http: HttpClient) {
  }

  createUserProfile(userDetails) {
    return this.http.post<ResponseDTO>(this.baseUrl + 'user/profile/createProfile', userDetails);
  }
}
