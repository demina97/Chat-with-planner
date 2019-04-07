import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {ChatComponent} from "./components/chat/chat.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {PlannerComponent} from "./components/planner/planner.component";
import {LoginGuard} from "./guards/login.guard";

const routes: Routes = [
  {path: 'planner', component: PlannerComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'chat', component: ChatComponent, canActivate: [LoginGuard]},
  {path: '**', component: LoginPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
