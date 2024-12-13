import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private signUpApi: string = "http://localhost:8000/auth/sign-up";
  private loginApi: string = "http://localhost:8000/api/token";
  private getUserByUsernameApi: string = "http://localhost:8000/auth/user";

  constructor(private httpClient: HttpClient) { }

  signUp(user: any): Observable<any> {
    return this.httpClient.post(this.signUpApi, user);
  }

  login(user: any) : Observable<any> {
    return this.httpClient.post(this.loginApi, user);
  }

  getUserByUsername(token: any): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization : 'Bearer ' + token
      })
    };

    return this.httpClient.get(this.getUserByUsernameApi, httpOptions);
  }

}
