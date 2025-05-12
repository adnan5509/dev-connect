import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDashboardData } from '../model/user-dashboard-data';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private httpClient: HttpClient) { }


  getUserDashboardData(username: string): Observable<UserDashboardData> {
    return this.httpClient.get<UserDashboardData>(`http://localhost:8080/dashboard/${username}`);
  }
}
