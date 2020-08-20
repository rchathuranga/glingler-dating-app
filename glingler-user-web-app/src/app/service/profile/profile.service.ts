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

  async getLocationData(userDetails) {
    if (navigator.geolocation) {
      console.log('it is supported');
      await navigator.geolocation.getCurrentPosition(position => {
        console.log(position);
        console.log(position.coords.latitude);
        console.log(position.coords.longitude);

        userDetails = {
          ...{longitude: position.coords.longitude},
          latitude: position.coords.latitude
        };
      });
    } else {
      console.log('geo location is not supported');
    }
    return userDetails;
  }

  createUserProfile(userDetails) {
    this.getLocationData(userDetails).then(r => userDetails = r);
    console.log('user details profile service 36', userDetails);

    return this.http.post<ResponseDTO>(this.baseUrl + 'user/profile', userDetails);
  }


  getProfileData() {
    return this.http.get<ResponseDTO>(this.baseUrl + 'user/profile');
  }
}
