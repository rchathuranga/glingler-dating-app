import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ProfileDTO} from '../../dto/profile-d-t-o';
import {ChatService} from '../../service/chat/chat.service';
import {ChatDTO} from '../../dto/chat-dto';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {

  @ViewChild('scrollMe') private myScrollContainer: ElementRef;
  @ViewChild('message') private input: ElementRef;

  profiles: ProfileDTO[];
  activeChat: ProfileDTO;
  serverChats: ChatDTO[] = [];
  fireChats: ChatDTO[] = [];
  chats: ChatDTO[] = [];
  matchedId: number;
  prevMatchedId: number;
  profileId: number = +(localStorage.getItem('profileId'));

  constructor(private chatService: ChatService) {
    this.prevMatchedId = 0;
    this.matchedId = 0;
    this.activeChat = {firstName: '', lastName: ''};
    chatService.getChatProfiles().subscribe(res => {
      this.profiles = res.data;
      if (this.profiles[0] !== undefined) {
        this.activeChat = this.profiles[0];
      }
    }, error => {
      console.log(error);
    });
  }

  functionIsActive() {
    // this.chatService.getFireDBActiveRef()
    // todo set active status to profile
  }

  ngOnInit(): void {
    // this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
  }

  inputKeyEvent($event: KeyboardEvent) {
    if ($event.key === 'Enter') {
      this.sendMsg(this.input.nativeElement.value);
    }
  }

  btnSendClickEvent() {
    this.sendMsg(this.input.nativeElement.value);
  }

  profileCardClickEvent(profile: ProfileDTO) {
    this.activeChat = profile;
    this.prevMatchedId = this.matchedId;
    this.matchedId = profile.matchedIdWithUser;

    // =================================
    if (this.prevMatchedId !== 0) {
      this.sendChatHistoryToServer(this.prevMatchedId);
    }
    // =================================
    this.chats = [];
    this.fireChats = [];
    this.serverChats = [];

    this.chatService.getChatMessages(this.activeChat.profileId).subscribe(res => {
      this.serverChats = res.data;

      this.chatService.getFireDBMatchRef(this.matchedId).snapshotChanges(['child_added']).subscribe(resp => {
        this.fireChats = [];
        resp.forEach(value => {
          const data: ChatDTO = value.payload.toJSON();
          this.fireChats.push(data);
        });

        this.chats = this.serverChats.concat(this.fireChats);
        console.log(this.chats);
      }, err => {
        alert(err);
      });
    }, error => {
      console.log(error);
    });
  }

  ngOnDestroy() {
    this.sendChatHistoryToServer(this.matchedId);
  }

  private sendMsg(value: string) {
    if (value === '') {
      return;
    }

    const data = {
      createdTime: new Date().getTime().toString(),
      message: value,
      sendProfileId: this.profileId
    };

    this.chatService.getFireDBMatchRef(this.matchedId).push(data);
    this.input.nativeElement.value = '';
    this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
  }

  private sendChatHistoryToServer(matchedId) {
    const data = {
      matchedId,
      chats: this.fireChats
    };

    this.chatService.saveChatsToServer(data).subscribe(res => {
      console.log(res);
      this.chatService.getFireDBMatchRef(matchedId).remove().then().catch();
    }, err => {
      console.log(err);
    });
  }


}
