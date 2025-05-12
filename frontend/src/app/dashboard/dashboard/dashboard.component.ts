import { Component, OnDestroy, OnInit } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy {

  subscriptions: Subscription[] = [];
  totalProjects!: number;
  totalTasks!: number;
  completedTasks!: number;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    const storedUserData = JSON.parse(localStorage.getItem('logged-in-user') || 'null');
    if (storedUserData) {

      const username = storedUserData.username;
      this.subscriptions.push(
        this.dashboardService.getUserDashboardData(username).subscribe(
          {
            next: (responseData) => {
              console.log(responseData);
              this.totalProjects = responseData.totalProjects;
              this.totalTasks = responseData.totalTasks;
              this.completedTasks = responseData.completedTasks;

            }
          }
        )
      );
    }

  }

  ngOnDestroy() {
    this.subscriptions.forEach(
      (subscription) => subscription.unsubscribe()
    );
  }


}
