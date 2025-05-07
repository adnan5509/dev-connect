import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { UserRegisterRequest } from 'src/app/model/userRegisterRequest';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private fb: FormBuilder, private authService: AuthService) { }

  signupForm!: FormGroup;

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      bio: [''],
      githubUrl: ['']
    })
  }

  onSubmit() {
    if (this.signupForm.valid) {

      const userRegisterRequest: UserRegisterRequest = {
        username: this.signupForm.value.username,
        password: this.signupForm.value.password,
        email: this.signupForm.value.email,
        authorities: [
          { authority: "ROLE_NEW_USER" }
        ],
        bio: this.signupForm.value.bio,
        githubUrl: this.signupForm.value.githubUrl,
      }

      this.authService.signup(userRegisterRequest).subscribe({
        next: resposeData => console.log(resposeData),
        error: error => console.log(error)
      })


    }
  }

}
