import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatchService} from '../../service/match/match.service';
import {ProfileDTO} from '../../dto/profile-d-t-o';
import {ChatService} from '../../service/chat/chat.service';
import {ChatDTO} from '../../dto/chat-dto';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  profiles: ProfileDTO[];
  activeChat: ProfileDTO;
  chats: ChatDTO[];

  @ViewChild('scrollMe') private myScrollContainer: ElementRef;
  @ViewChild('message') private input: ElementRef;

  constructor(private matchService: MatchService, private chatService: ChatService) {
    matchService.getMatchedProfiles().subscribe(res => {
      this.profiles = res.data;
      if (this.profiles[0] !== undefined) {
        this.activeChat = this.profiles[0];
      }
    }, error => {
    });
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

  private sendMsg(value: string) {
    if (value === '') {
      return;
    }
    this.chats.push({
      chatClass: 'my-msg',
      message: value,
      sendProfileId: +(localStorage.getItem('profileId'))
    });
    this.input.nativeElement.value = '';
    this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
  }

  profileCardClickEvent(profile: ProfileDTO) {
    this.activeChat = profile;
    this.chatService.getChatMessages(this.activeChat.profileId).subscribe(res => {
      console.log(res);
      this.chats = res.data;
      const id: number = +(localStorage.getItem('profileId'));
      res.data.forEach((value: ChatDTO) => {
        if (value.sendProfileId === id) {
          value.chatClass = 'my-msg';
        } else {
          value.chatClass = 'her-msg';
        }
      });

    }, error => {
      console.log(error);
    });
  }
}
