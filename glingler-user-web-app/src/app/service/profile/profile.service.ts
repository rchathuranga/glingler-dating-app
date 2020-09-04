import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ResponseDTO} from '../../dto/response-dto';
import {AngularFireDatabase, AngularFireList, AngularFireObject} from '@angular/fire/database';
import {ChatDTO} from '../../dto/chat-dto';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  baseUrl = environment.glingler_api_base_url;

  constructor(private http: HttpClient, private db: AngularFireDatabase) {
  }

  getFirebaseDBRef(profileId): AngularFireObject<any> {
    return this.db.object('active/profile/' + profileId);
  }

  createUserProfile(userDetails) {
    return this.http.post<ResponseDTO>(this.baseUrl + 'user/profile/create', userDetails);
  }


  getProfileData() {
    return this.http.get<ResponseDTO>(this.baseUrl + 'user/profile');
  }

  getMatchProfiles() {
    return this.http.get<ResponseDTO>(this.baseUrl + 'user/matches/profiles');
  }
}
