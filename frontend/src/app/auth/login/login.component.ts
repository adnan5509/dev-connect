import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { UserLoginResponse } from 'src/app/model/user-login-response';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) { }

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const email = this.loginForm.value.email;
      const password = this.loginForm.value.password;

      this.authService.login(email, password).subscribe(
        {
          next: (responseData: UserLoginResponse) => {
            localStorage.setItem('token', responseData.token);
            localStorage.setItem('logged-in-user', JSON.stringify(responseData))
            this.router.navigate(['dashboard']);
          },
          error: (error) => console.log(error)
        }

      );
    }
  }

}
