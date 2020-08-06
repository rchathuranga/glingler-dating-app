import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {AnalyticsComponent} from './analytics/analytics.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ProfileManagementComponent} from './profile-management/profile-management.component';


const routes: Routes = [
  {
    path: '',
    component: AdminHomeComponent,
    children: [
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'analytic',
        component: AnalyticsComponent
      },
      {
        path: 'profile',
        component: ProfileManagementComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminAppRoutingModule {
}
