import { Component, OnInit } from '@angular/core';
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {Task} from "../../models/Task";

@Component({
  selector: 'app-planner',
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.css']
})
export class PlannerComponent implements OnInit {
  tasks : Task[] = [];
  task = new Task();
  selectedDate: NgbDateStruct;

  constructor() { }

  ngOnInit() {
  }

  addTask(value : string){
    this.task.taskText = value;
    this.task.taskStatus = false;
    this.tasks.push(this.task);
    this.task.taskText = "";
    console.log(this.tasks);
  }

  deleteItem(task) {
    for(let i = 0; i <= this.tasks.length; i++){
      if(task == this.tasks[i]){
        this.tasks.splice(i, 1);
      }
    }
  }

  getTasks() {
    console.log(this.selectedDate);
  }
}
