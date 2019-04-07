import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-planner',
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.css']
})
export class PlannerComponent implements OnInit {
  tasks = [];
  task: string = "";
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

}
