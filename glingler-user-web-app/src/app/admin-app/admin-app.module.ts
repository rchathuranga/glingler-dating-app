import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AdminAppRoutingModule} from './admin-app-routing.module';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatRadioModule} from '@angular/material/radio';
import {MatListModule} from '@angular/material/list';
import {MatIconModule} from '@angular/material/icon';
import {AnalyticsComponent} from './analytics/analytics.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ProfileManagementComponent} from './profile-management/profile-management.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatBadgeModule} from '@angular/material/badge';
import {MatMenuModule} from '@angular/material/menu';
import {MatProgressBarModule} from '@angular/material/progress-bar';

@NgModule({
  declarations: [AdminHomeComponent, AnalyticsComponent, DashboardComponent, ProfileManagementComponent],
  imports: [
    CommonModule,
    AdminAppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatRadioModule,
    MatListModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatGridListModule,
    MatBadgeModule,
    MatMenuModule,
    MatProgressBarModule,
  ]
})
export class AdminAppModule { }
