import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserRegisterRequest } from '../model/user-register-request';
import { UserLoginResponse } from '../model/user-login-response';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  login(email: string, password: string): Observable<UserLoginResponse> {
    return this.httpClient.post<UserLoginResponse>('http://localhost:8080/user/login', {
      email: email,
      password: password
    });

  }

  signup(userRegisterRequest: UserRegisterRequest) {
    return this.httpClient.post('http://localhost:8080/user/signup', userRegisterRequest);
  }
}
