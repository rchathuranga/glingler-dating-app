import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {AuthGuard} from './guard/auth.guard';
import {FilterComponent} from './filter/filter.component';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'login'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'sign-up',
    component: SignUpComponent
  },
  {
    path: 'filter',
    component: FilterComponent
  },
  {
    path: 'application',
    loadChildren: () => import('./application/application.module').then(module => module.ApplicationModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin-app/admin-app.module').then(module => module.AdminAppModule),
  },
  // {
  //   path: '**',
  //   redirectTo: 'login'
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
