import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {LoginData} from "../models/LoginData";
import {map} from "rxjs/operators";
import {ChatService} from "./chat.service";
import {TokenService} from "./token.service";
import {User} from "../models/User";
import {Router} from "@angular/router";
import {Task} from '../models/Task';
import {LoginService} from "./login.service";

@Injectable({
  providedIn: 'root'
})
export class PlannerService {

  constructor(private http: HttpClient, private loginService: LoginService) {
  }

  saveTask(task): Observable<Task[]>{
    return this.http.post<any>(environment.server_url + '/api_planner/saveTask', task);
  }

  loadTasks(taskDate: Date): Observable<Task[]>{
    return this.http.post<any>(environment.server_url + '/api_planner/getTasks',
     {phone: this.loginService.userInfo.phone, date: taskDate});
  }

  deleteTask(taskId: string): Observable<any>{
    return this.http.post<any>(environment.server_url + '/api_planner/deleteTask?id=' + encodeURIComponent(taskId), {});
  }

}
