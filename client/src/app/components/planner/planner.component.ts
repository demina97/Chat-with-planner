import { Component, OnInit } from '@angular/core';
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-planner',
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.css']
})
export class PlannerComponent implements OnInit {
  tasks = [];
  task: string = "";
  selectedDate: NgbDateStruct;
  constructor() { }

  ngOnInit() {
  }

  addTask(value : String){
    this.tasks.push(value);
    this.task = "";
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
