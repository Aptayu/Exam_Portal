import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ngOnInit(): void { }
  loginData = {
    username: '',
    password: ''
  }
  constructor(private snack: MatSnackBar, private login: LoginService, private router: Router) {

  }
  formSubmit() {
    console.log("login form submitted");
    if (this.loginData.username.trim() == '' || this.loginData.username == null) {
      this.snack.open("username is required !!", 'ok', {
        duration: 3000,
      });
      return;
    }
    if (this.loginData.password.trim() == '' || this.loginData.password == null) {
      this.snack.open("Password is required !!", 'ok', {
        duration: 3000,
      });
      return;
    }
    // request service to generate token(here comes the use and why we need service so that it should be kept separetly and handles all login related actions)
    this.login.generateToken(this.loginData).subscribe(
      (data: any) => {
        console.log("success");
        console.log(data);

        // after successful generation to token we need to login
        // To add a token to all requests in an Angular application after it has been generated, you can use an HTTP Interceptor.
        this.login.loginUser(data.token);
        this.login.getCurrentUser().subscribe(
          (user: any) => {
            this.login.setUser(user);
            console.log(user);

            // redirect accoring to ADMIN or Normal pages, but keep in mind we need to send token for every request now onwards
            if (this.login.getUserRole() == "Admin") {
              // admin dashboard
              window.location.href = '/adminhome';
              // this.router.navigate(['/adminhome']);
            }
            else if (this.login.getUserRole() == 'Normal') {
              // normal user dashboard
              // this.router.navigate(['/userhome']);
              window.location.href = '/userhome';
            }
            else {
              this.login.logOut();
            }
          }
        )
      },
      (error: any) => {
        console.log("error");
        console.log(error);
        console.error();
        this.snack.open("Invalid details !! Try again", 'ok', {
          duration: 3000,
        })
      }

    );
  }

}


