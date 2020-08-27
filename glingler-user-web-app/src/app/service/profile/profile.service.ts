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
    console.log('user details profile service 36', userDetails);
    return this.http.post<ResponseDTO>(this.baseUrl + 'user/profile', userDetails);
  }


  getProfileData() {
    return this.http.get<ResponseDTO>(this.baseUrl + 'user/profile');
  }

  getMatchProfiles() {
    return this.http.get<ResponseDTO>(this.baseUrl + 'user/matches/profiles');
  }
}
