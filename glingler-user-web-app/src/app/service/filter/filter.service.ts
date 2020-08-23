import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ResponseDTO} from '../../dto/response-dto';

@Injectable({
  providedIn: 'root'
})
export class FilterService {

  baseUrl = environment.glingler_api_base_url;

  constructor(private http: HttpClient) {
  }

  updateProfileFilter(data) {
    return this.http.put<ResponseDTO>(this.baseUrl + 'user/profile/update-filters',data);
  }

}
