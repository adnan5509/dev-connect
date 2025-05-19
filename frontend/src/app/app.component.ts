import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';


  projects = [
    { name: 'Project A', createdBy: 'Adnan' },
    { name: 'Project B', createdBy: 'Sara' }
  ];

  tasks = [
    { taskName: 'Fix Bug', assignedTo: 'Ali', priority: 'High' },
    { taskName: 'Code Review', assignedTo: 'Noor', priority: 'Medium' }
  ];

  projectDisplayedColumns: string[] = ['name', 'createdBy'];
  taskDisplayedColumns: string[] = ['taskName', 'assignedTo', 'priority'];


}
