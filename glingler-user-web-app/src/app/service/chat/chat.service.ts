import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ResponseDTO} from '../../dto/response-dto';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  baseUrl = environment.glingler_api_base_url;

  constructor(private httpClient: HttpClient) {
  }

  getChatMessages(opId) {
    return this.httpClient.get<ResponseDTO>(this.baseUrl + 'user/chat/msg/'+opId);
  }
}
