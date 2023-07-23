import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';




@Component({
  selector: 'app-userhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.css']
})
export class AdminhomeComponent {
  
  
  // Initializing the queryParams property as an object (empty object {} in this case) ensures that the property exists and has a valid object reference.
  queryParams: any = {};
  selectedOptions: any[] = [];
  quizForm: FormGroup;
  category: string | undefined;
  constructor(
    private quizService: QuizService,
    private router: Router,
    private formBuilder: FormBuilder,
  ) { 
    this.quizForm = this.formBuilder.group({
      category: ['', Validators.required],
      difficulty: ['', Validators.required],
      amount: ['', Validators.required],
      type: ['', Validators.required]
    });


  }
  onSubmit(quizForm: any) {
    console.log("inside on submit of admin quiz requests");
    
    console.log(quizForm);
    
    console.log("form amount + " + quizForm.amount)
    this.queryParams = { ...quizForm }; // Copy form data to queryParams object
    console.log("amount" + this.queryParams.amount);
    
    this.router.navigate(['/question'],{ queryParams: this.queryParams});
    
    
  }
 
}