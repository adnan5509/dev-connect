import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProjectListComponent } from './project-list/project-list.component';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { ProjectDetailsCardComponent } from './project-details-card/project-details-card.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthComponent } from './auth/auth.component';
import { LoginComponent } from './auth/login/login.component';
import { Router, RouterModule } from '@angular/router';
import { routes } from './app.routes';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { SignupComponent } from './auth/signup/signup.component';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { DashboardComponent } from './dashboard/dashboard/dashboard.component';
import { DashboardCardComponent } from './dashboard/dashboard-card/dashboard-card.component';
import { SideBarNavComponent } from './sidebar/side-bar-nav/side-bar-nav.component';

@NgModule({
  declarations: [
    AppComponent,
    ProjectListComponent,
    ProjectDetailsCardComponent,
    AuthComponent,
    LoginComponent,
    SignupComponent,
    DashboardComponent,
    DashboardCardComponent,
    SideBarNavComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatChipsModule,
    MatInputModule,
    MatFormFieldModule,
    HttpClientModule,
    RouterModule,
    RouterModule.forRoot(routes),
    ReactiveFormsModule,
    MatSelectModule,     // Add MatSelectModule here
    MatOptionModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule   // Add MatOptionModule here
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
