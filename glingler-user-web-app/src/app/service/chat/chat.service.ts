import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ResponseDTO} from '../../dto/response-dto';
import {AngularFireDatabase, AngularFireList, AngularFireObject} from '@angular/fire/database';
import {ChatDTO} from '../../dto/chat-dto';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  baseUrl = environment.glingler_api_base_url;

  constructor(private httpClient: HttpClient, private db: AngularFireDatabase) {
  }

  getChatProfiles() {
    return this.httpClient.get<ResponseDTO>(this.baseUrl + 'user/matches/matched');
  }

  getChatMessages(opId) {
    return this.httpClient.get<ResponseDTO>(this.baseUrl + 'user/chat/msg/' + opId).toPromise();
  }

  getFireDBMatchRef(matchedId): AngularFireList<ChatDTO> {
    console.log('db:', this.db);
    console.log('matchedId:', matchedId);
    return this.db.list<ChatDTO>('/chats/matched/' + matchedId);
  }

  getFireDBActiveRef(profileId): AngularFireObject<any> {
    return this.db.object('active/profile/' + profileId);
  }

  saveChatsToServer(data) {
    return this.httpClient.post<ResponseDTO>(this.baseUrl + 'user/chat/saveChat', data);
  }
}
