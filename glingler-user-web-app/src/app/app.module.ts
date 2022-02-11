import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AngularFireModule} from '@angular/fire';
import {AngularFireDatabaseModule} from '@angular/fire/database';
import {AngularFireStorageModule} from '@angular/fire/storage';
import {AngularFireMessagingModule} from '@angular/fire/messaging';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {SignUpComponent} from './sign-up/sign-up.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthenticateService} from './service/authenticate/authenticate.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatInputModule} from '@angular/material/input';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthGuard} from './guard/auth.guard';
import {TokenInterceptor} from './interceptor/token.interceptor';
import { FilterComponent } from './filter/filter.component';
import {environment} from '../environments/environment';
import {ServiceWorkerModule} from '@angular/service-worker';
import { SmAlertDialogComponent } from './shared/components/sm-alert-dialog/sm-alert-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import { SmAlertComponent } from './shared/components/sm-alert/sm-alert.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
    FilterComponent,
    SmAlertDialogComponent,
    SmAlertComponent,
  ],
    imports: [
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        MatNativeDateModule,
        // ServiceWorkerModule.register('/ngsw-worker.js', { enabled:  environment.production }),
        AngularFireModule.initializeApp(environment.firebaseConfig),
        AngularFireStorageModule,
        AngularFireMessagingModule,
        AngularFireDatabaseModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatButtonModule,
        MatCardModule,
        MatIconModule,
        MatListModule,
        MatInputModule,
        MatRippleModule,
        MatDatepickerModule,
        MatSelectModule,
        FormsModule,
        MatDialogModule,
        ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production }),
    ],
  providers: [AuthenticateService, AuthGuard, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi: true
  }],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
