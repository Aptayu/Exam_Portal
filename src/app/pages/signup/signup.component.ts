import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  constructor(
    private userService: UserService,
    private snackBar: MatSnackBar
  ){

  }
  public user={
    userName:'',
    password:'',
    email: '',
    firstname:'',
    lastname:''

  }
  ngOnInit(): void {
  }
  formSubmit(){
    // alert('form submitted');
    console.log(this.user);
    if(this.user.userName == '' || this.user.userName == null){
      // alert('User is required !!')
      this.snackBar.open("Username is required", '', {
        duration: 3000,
      })
      return;
    }

    // add user function which is there in userservice which will return an
    // Observable whihc you need to subscribe
    this.userService.addUser(this.user).subscribe(
        (data) =>{
          // success
          // console.log(data);
          // alert("success")
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Registration successful !!',
            showConfirmButton: true,
            timer: 5000
          }) 
         
        },
        (error)=>{
          // error
          // console.log(error);
          // alert("something went wrong");
          Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Something went wrong!',
          })
        }
      )


  }

}
