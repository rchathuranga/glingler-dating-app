import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainAppComponent} from './main-app/main-app.component';
import {UserFeedComponent} from './user-feed/user-feed.component';
import {ChatComponent} from './chat/chat.component';
import {ProfileComponent} from './profile/profile.component';
import {ExplorerComponent} from './explorer/explorer.component';

const routes: Routes = [
  {
    path: '',
    component: MainAppComponent,
    children: [
      {
        path: '',
        redirectTo: 'feed'
      },
      {
        path: 'feed',
        component: UserFeedComponent,
        data: {
          animation: 'isRight'
        }
      },
      {
        path: 'chat',
        component: ChatComponent,
        data: {
          animation: 'isLeft'
        }
      },
      {
        path: 'profile',
        component: ProfileComponent,
      },
      {
        path: 'explorer',
        component: ExplorerComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ApplicationRoutingModule {
}
