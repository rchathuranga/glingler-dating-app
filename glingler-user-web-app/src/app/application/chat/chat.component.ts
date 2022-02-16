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

  isImagesLoaded = false;
  profiles: ProfileDTO[];
  profilesView: ProfileDTO[];
  activeChat: ProfileDTO;
  serverChats: ChatDTO[] = [];
  fireChats: ChatDTO[] = [];
  chats: ChatDTO[] = [];
  areChatsLoading = true;
  matchedId: number;
  prevMatchedId: number;
  profileId: number = +(localStorage.getItem('profileId'));
  chatSearch = '';

  constructor(private chatService: ChatService) {
    this.prevMatchedId = 0;
    this.matchedId = 0;
    this.activeChat = {firstName: '', lastName: ''};
    chatService.getChatProfiles().subscribe(res => {
      this.profiles = res.data;
      this.profilesView = this.profiles;
      if (this.profiles[0] !== undefined) {
        this.activeChat = this.profiles[0];
        this.profileCardClickEvent(this.activeChat);
      }

      this.profiles.forEach(prof => {
        this.functionIsActive(prof);
      });
    }, error => {
      console.log(error);
    });
  }

  functionIsActive(profile: ProfileDTO) {
    this.chatService.getFireDBActiveRef(profile.profileId).snapshotChanges().subscribe(resp => {
      const response = resp.payload.toJSON();
      console.log('resss : ', response);
      profile.isActive = response['status'] === 'ACTIVE';
      profile.lastActiveTime = new Date(response['LIA_TIME']);
    });
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

  async profileCardClickEvent(profile: ProfileDTO) {
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

    await this.chatService.getChatMessages(this.activeChat.profileId).then(res => {
      this.serverChats = res.data;
      this.chats = this.serverChats;
      this.areChatsLoading = false;
      if (this.serverChats == null) {
        this.serverChats = [];
      }
    }).catch(error => {
      console.log(error);
    });

    this.chatService.getFireDBMatchRef(this.matchedId).snapshotChanges(['child_added', 'child_removed']).subscribe(resp => {
      this.fireChats = [];
      resp.forEach(value => {
        const data: ChatDTO = value.payload.toJSON();
        this.fireChats.push(data);
        this.chats = this.serverChats.concat(this.fireChats);
        this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
      });
    }, err => {
      alert(err);
    });
  }

  ngOnDestroy() {
    this.sendChatHistoryToServer(this.matchedId);
  }

  private sendMsg(value: string) {
    console.log('msg:', value);
    console.log('matchedId:', this.matchedId);
    if (value === '' || this.matchedId === 0) {
      this.input.nativeElement.value = '';
      return;
    }

    const data = {
      createdTime: new Date().getTime().toString(),
      message: value,
      sendProfileId: this.profileId
    };
    console.log('data:', data);
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


  searchOnChange(event) {
    this.profilesView = [];
    this.profiles.forEach(value => {
      const firstNameCheck = value.firstName.toLowerCase().startsWith(this.chatSearch);
      const lastNameCheck = value.lastName.toLowerCase().startsWith(this.chatSearch);
      if (firstNameCheck || lastNameCheck) {
        this.profilesView.push(value);
      }
    });
  }
}
