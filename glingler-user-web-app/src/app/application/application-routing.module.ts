import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainAppComponent} from './main-app/main-app.component';
import {UserFeedComponent} from './user-feed/user-feed.component';
import {ChatComponent} from './chat/chat.component';

const routes: Routes = [
  {
    path: '',
    component: MainAppComponent,
    children: [
      // {
      //   path: '',
      //   redirectTo: 'feed'
      // },
      {
        path: 'feed',
        component: UserFeedComponent,
      },
      {
        path: 'chat',
        component: ChatComponent,
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
