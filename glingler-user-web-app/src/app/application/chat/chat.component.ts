import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  profiles: any = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  chats: any = [
    {
      c: 'my-msg',
      m: 'Hello'
    },
    {
      c: 'her-msg',
      m: 'Hi'
    },
    {
      c: 'my-msg',
      m: '💕'
    },
    {
      c: 'my-msg',
      m: '😘'
    },
    {
      c: 'her-msg',
      m: 'Ummah'
    },
  ];

  @ViewChild('scrollMe') private myScrollContainer: ElementRef;
  @ViewChild('message') private input: ElementRef;

  constructor() {
  }

  ngOnInit(): void {
    this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
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
      c: 'my-msg',
      m: value
    });
    this.input.nativeElement.value = '';
    this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
  }
}
