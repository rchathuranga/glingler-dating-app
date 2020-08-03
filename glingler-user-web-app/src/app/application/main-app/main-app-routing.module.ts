import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainAppComponent} from './main-app.component';


const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: MainAppComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainAppRoutingModule {
}
