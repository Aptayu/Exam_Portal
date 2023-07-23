import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  isLoggedIn: boolean | undefined;

  username: string | undefined;
  constructor(public login: LoginService, private router: Router){}
//   So when you use a getter method in an HTML template with parentheses, Angular assumes that you are trying to call a method that takes arguments, but your getter method doesn't take any arguments. Therefore, Angular throws the error "This expression is not callable because it is a 'get' accessor. Did you mean to use it without '()'?"
// Hence, you should use getter methods in your HTML templates without parentheses.
  // get isLoggedIn(): boolean {
  //   return this.login.isLoggedIn();
  // }
  // username = this.login.getUserName();
  // // console.log("username", username);
  
  ngOnInit() {
    this.isLoggedIn = this.login.isLoggedIn();
    console.log("isloggedin ", this.isLoggedIn);
    

    if (this.isLoggedIn) {
      this.login.getCurrentUser().subscribe(
        (response: any) => {
          console.log("User data:", response);
          this.username = response.userName;
          console.log("Username:", this.username);
        },
        (error: any) => {
          console.error("Error:", error);
        }
      );
    }
  }

  isRegisterPage(): boolean{
    return this.router.url ==='/signup'
  }
  isLoginPage(): boolean{
    return this.router.url === '/login'
  }
  onLogout(): void {
    this.login.logOut(); // call logout method in your LoginService
    this.isLoggedIn = false; // update flag to reflect user logout
  }
}
