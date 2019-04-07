import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainPageComponent} from "./components/main-page/main-page.component";
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {ChatComponent} from "./components/chat/chat.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {LoginGuard} from "./guards/login.guard";

const routes: Routes = [
  {path: 'home', component: MainPageComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'chat', component: ChatComponent, canActivate: [LoginGuard]},
  {path: '**', component: MainPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
