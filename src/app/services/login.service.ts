import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { map, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  // as this service need to request server it needs http client service
  constructor(private http: HttpClient) { }

  // generate token method we need to call it in login component ts file
  public generateToken(loginData: any) {
    console.log("generating token");
    //using pipe to  to log the HTTP response from the API in your Angular application to see if it contains any additional error information. 
    return this.http.post(`${baseUrl}/generate-token/`, loginData).pipe(
      tap(response => console.log(response)),
      map(response => response)

    );
  }
// get current user
  public getCurrentUser(){
    console.log("inside getCurrentUserMethod");
    
    return this.http.get(`${baseUrl}/current-user/`);
  }

  // after generating token we have to keep it somewhere till the time user logs out 
  // where? in local storage

  // login user: set token in local storage
  public loginUser(token: string) {
    localStorage.setItem('token', token);
    return true;
  }
  // check whether user is logged in based on whether user has token or not
  public isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    }
    else {
      return true;
    }
  }
  // logout user
  public logOut() {
    localStorage.removeItem('token');
    return true;
  }
  // get token
  public getToken() {
    return localStorage.getItem('token');
  }
  // lets get user details so that we don't have to fire requests every time to server
  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }
  public getUser() {
    return localStorage.getItem('user');
  }

  // also if you need role of user
  
  public getUserRole() {
    // nullish coalescing operator (??) to provide a default empty object {} if the localStorage.getItem('user') expression 
    console.log('getUserRole called');
    const user = JSON.parse(localStorage.getItem('user')  ?? '{}');
   
    const userRole = localStorage.getItem('user');
    console.log('getUserRole:', user.authorities[0].authority);
    if (user && user.authorities) {
      return user.authorities[0].authority;
    } else {
      return null;
    }
  }
  public  getUserName(){
    
    this.getCurrentUser().subscribe((response: any) => {
      console.log("User data:", response);
      const username = response.userName;
      console.log("Username:", username);
      return username;
    },
    (error: any) => {
      console.error("Error:", error);
    }
    );
    
  }
  
}
