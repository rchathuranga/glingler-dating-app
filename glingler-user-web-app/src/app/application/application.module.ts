import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ApplicationRoutingModule} from './application-routing.module';
import {MainAppComponent} from './main-app/main-app.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatTabsModule} from '@angular/material/tabs';
import {MatIconModule} from '@angular/material/icon';
import {MatSliderModule} from '@angular/material/slider';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatCardModule} from '@angular/material/card';
import {RouterModule} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {MatRippleModule} from '@angular/material/core';
import {UserFeedComponent} from './user-feed/user-feed.component';
import {MatInputModule} from '@angular/material/input';
import {MatBadgeModule} from '@angular/material/badge';
import {MatMenuModule} from '@angular/material/menu';
import {MatGridListModule} from '@angular/material/grid-list';
import { ChatComponent } from './chat/chat.component';


@NgModule({
  declarations: [MainAppComponent, UserFeedComponent, ChatComponent],
  imports: [
    RouterModule,
    CommonModule,
    ApplicationRoutingModule,
    MatDividerModule,
    MatTabsModule,
    MatIconModule,
    MatSliderModule,
    MatTooltipModule,
    MatCardModule,
    MatButtonModule,
    MatRippleModule,
    CommonModule,
    CommonModule,
    MatInputModule,
    MatBadgeModule,
    MatMenuModule,
    MatGridListModule
  ]
})
export class ApplicationModule {
}
