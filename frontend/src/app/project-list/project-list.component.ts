import { Component } from '@angular/core';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { HttpClient } from '@angular/common/http';
import { Project } from '../model/project';


@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css'],

})
export class ProjectListComponent {

  Projects: Project[] = [];

  constructor(private http: HttpClient) {

  }

  ngOnInit(): void {
    this.http.get<Project[]>('http://localhost:8080//api/projects/user/1').subscribe((data) => {
      this.Projects = data;
    });
  }
}
