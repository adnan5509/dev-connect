import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-dashboard-card',
  templateUrl: './dashboard-card.component.html',
  styleUrls: ['./dashboard-card.component.css']
})
export class DashboardCardComponent {

  @Input({ required: true }) dashboardCardTitle!: string;
  @Input({ required: true }) dashboardCardValue!: any;

}
