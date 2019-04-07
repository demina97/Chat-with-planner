import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {FormsModule} from "@angular/forms";
import {LoginPageComponent} from './components/login-page/login-page.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ChatComponent} from './components/chat/chat.component';
import {MainPageComponent} from './components/main-page/main-page.component';
import {Interceptor} from "./app.interceptor";
import { RegistrationComponent } from './components/registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginPageComponent,
    ChatComponent,
    MainPageComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
