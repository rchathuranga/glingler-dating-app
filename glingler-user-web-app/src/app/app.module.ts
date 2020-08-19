import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AngularFireModule} from '@angular/fire';
import {AngularFireStorageModule} from '@angular/fire/storage';

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
import {MatRippleModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSelectModule} from '@angular/material/select';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthGuard} from './guard/auth.guard';
import {TokenInterceptor} from './interceptor/token.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    AngularFireModule.initializeApp({
      apiKey: 'AIzaSyCx3Hcbb9SGvMlrPp8M1uKgB8g1KShtmxI',
      authDomain: 'glingler-2a90b.firebaseapp.com',
      databaseURL: 'https://glingler-2a90b.firebaseio.com',
      projectId: 'glingler-2a90b',
      storageBucket: 'glingler-2a90b.appspot.com',
      messagingSenderId: '1016024450731',
      appId: '1:1016024450731:web:1d4f66391135534527d9ee',
      measurementId: 'G-TPG6LLKKY0'
    }),
    AngularFireStorageModule,
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
