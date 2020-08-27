import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ResponseDTO} from '../../dto/response-dto';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

  baseUrl = environment.glingler_api_base_url;

  constructor(private http: HttpClient) {
  }

  matchReaction(data) {
    return this.http.post<ResponseDTO>(this.baseUrl + 'user/matches/react', data);
  }

  getMatchedProfiles() {
    return this.http.get<ResponseDTO>(this.baseUrl + 'user/matches/matched');
  }
}
