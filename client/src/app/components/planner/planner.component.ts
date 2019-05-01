import { Component, OnInit } from '@angular/core';
import {NgbDate, NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {Task} from "../../models/Task";
import {LoginService} from "../../services/login.service";
import {PlannerService} from "../../services/planner.service";
import {TaskStatus} from "../../models/TaskStatus";

@Component({
  selector: 'app-planner',
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.css']
})
export class PlannerComponent implements OnInit {
  tasks : Task[] = [];
  task = new Task();
  selectedDate: NgbDateStruct = new NgbDate(new Date().getFullYear(), new Date().getMonth()+1, new Date().getDate()+1);
  statusList: TaskStatus[];

  constructor(private loginService: LoginService, private plannerService: PlannerService) { }

  ngOnInit() {
    this.plannerService.loadStatus().subscribe(value => {
      this.statusList = value;
    });
    this.getTasks();
  }

  addTask(value : string){
    this.task.taskText = value;
    this.task.taskStatus = 'NEW';
    this.task.taskOwner = this.loginService.userInfo.phone;
    this.task.taskDate = new Date(this.selectedDate.year, this.selectedDate.month-1, this.selectedDate.day);
    this.plannerService.saveTask(this.task).subscribe(value1 => this.tasks = value1);
    this.task.taskText = "";
  }

  deleteItem(task) {
    this.plannerService.deleteTask(task).subscribe(()=> this.getTasks())
  }

  getTasks() {
    this.plannerService
      .loadTasks(new Date(this.selectedDate.year, this.selectedDate.month-1, this.selectedDate.day))
      .subscribe(value => this.tasks = value);
  }

  changeStatus(taskId: string, taskStatus: string) {
    this.plannerService.updateTaskStatus(taskId, taskStatus).subscribe(value => {
      this.getTasks()
    }, error => {
      this.getTasks()
    });
  }
}
