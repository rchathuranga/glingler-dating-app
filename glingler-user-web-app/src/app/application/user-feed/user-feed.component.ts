import {Component, OnInit} from '@angular/core';
import {ProfileService} from '../../service/profile/profile.service';
import {ProfileDTO} from '../../dto/profile-d-t-o';
import {animate, style, transition, trigger} from '@angular/animations';
import {MatchService} from '../../service/match/match.service';

@Component({
  selector: 'app-user-feed',
  templateUrl: './user-feed.component.html',
  styleUrls: ['./user-feed.component.css'],
  animations: [
    trigger(
      'CardAnimation',
      [
        transition(
          ':enter',
          [style({transform: 'scale(2)', opacity: .8}),
            animate('.5s ease-out', style({opacity: 1, transform: 'scale(1)'}))]
        ),
        transition(
          ':leave',
          [style({opacity: 1, transform: 'scale(1)'}), animate('.5s ease-in', style({opacity: 0, transform: 'scale(0)'}))
          ]
        )
      ]
    ),
    trigger(
      'inOutAnimation',
      [
        transition(
          ':enter',
          [style({top: '-200px', opacity: 0, height: 0}), animate('.5s ease-out', style({top: '0', opacity: 1, height: '40px'}))]
        ),
        transition(
          ':leave',
          [style({top: '0', opacity: 1, height: '40px'}), animate('.5s ease-in', style({top: '-200px', opacity: 0, height: 0}))
          ]
        )
      ]
    )
  ]
})
export class UserFeedComponent implements OnInit {
  notificationMsg = '';

  showStories = false;
  index: any = [1, 3, 4, 5, 6, 7];

  btnRippleColor = 'rgba(255,255,255,0.22)';
  matchProfileList: ProfileDTO[] = [];

  constructor(private profileService: ProfileService, private matchService: MatchService) {

  }

  ngOnInit(): void {

    this.profileService.getMatchProfiles().subscribe(res => {
      console.log(res.data);
      this.matchProfileList = res.data;
    }, error => {
      console.log(error);
    });

  }

  btnLikeClickEvent(matchProfile: ProfileDTO, index: number) {
    const data = {
      userProfileId: localStorage.getItem('profileId'),
      matchProfileId: matchProfile.profileId,
      actionType: 2
    };

    this.matchService.matchReaction(data).subscribe(res => {
      this.notificationMsg = '' + res.responseCode;
      this.matchProfileList.splice(index, 1);
    }, error => {
      this.notificationMsg = error.toString();
    });
    console.log(matchProfile);
    // this.notificationMsg = '';
  }

  btnSuperLikeClickEvent(matchProfile: ProfileDTO, index: number) {
    const data = {
      userProfileId: localStorage.getItem('profileId'),
      matchProfileId: matchProfile.profileId,
      actionType: 1
    };

    this.matchService.matchReaction(data).subscribe(res => {
      this.notificationMsg = '' + res.responseCode;
      this.matchProfileList.splice(index, 1);
    }, error => {
      this.notificationMsg = error.toString();
    });
    // alert('SuperLike');
    this.notificationMsg = 'Hello';
    this.matchProfileList.pop();
  }


  btnRejectClickEvent(matchProfile: ProfileDTO, index: number) {
    const data = {
      userProfileId: localStorage.getItem('profileId'),
      matchProfileId: matchProfile.profileId,
      actionType: 3
    };

    this.matchService.matchReaction(data).subscribe(res => {
      this.notificationMsg = '' + res.responseCode;
      this.matchProfileList.splice(index, 1);
    }, error => {
      this.notificationMsg = error.toString();
    });

    // alert('rejected');
    // this.showStories = !this.showStories;
  }
}
