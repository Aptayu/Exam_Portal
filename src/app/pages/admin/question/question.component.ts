import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent {
  quizData : any;
  queryParams: any = {};
  selectedOptions: any[] = [];
  constructor(
    private quizService: QuizService,
    private router: Router,
    private route: ActivatedRoute
  ) { }
  ngOnInit() {
    // Retrieve form data from query parameters
    console.log("inside ngOnIt of question component");
    
    this.route.queryParams.subscribe(params => {
      // The form data will be available in the 'params' object
      // Use the form data as needed
      // ...
      console.log("Trying to get form data in question component");
      
     this.queryParams = params
     console.log("amount" + this.queryParams.amount);
      console.log(this.queryParams);
      
    
    });
    this.quizService.getQuizQuestions(this.queryParams).subscribe((response: any) => {
      this.quizData = response.results;
      console.log(this.queryParams.type);
      
      if(this.queryParams.type === 'multiple'){
        this.quizData.forEach((question: any) => {
          this.shuffleOptions(question);
        });
      }
     
      console.log(this.quizData);
    });
  }

  shuffleOptions(question: any): any[] {
    const options = [question.correct_answer, ...question.incorrect_answers];
    const shuffledOptions = options.sort(() => Math.random() - 0.5);
    question.shuffledOptions = shuffledOptions;
    return shuffledOptions;
    
  }
  
  getAlphabet(index: number): string {
    return String.fromCharCode(65 + index);
  }
  selectOption(questionIndex: number, optionIndex: number): void {
    this.selectedOptions[questionIndex] = optionIndex;
  }
  isOptionSelected(questionIndex: number, optionIndex: number): boolean {
    return this.selectedOptions[questionIndex] === optionIndex;
  }

  submitQuiz(): void {
    // Check if the quiz data is valid (e.g., all questions answered)
    // ...
  
    // Assuming you have the quizData as an array of objects
    const quiz = {
      title: 'Quiz Title',
      questions: this.quizData,
      // Add any additional properties you want to pass to the QuizComponent
    };
  
    // Navigate to the QuizComponent and pass the quiz data
    this.router.navigate(['/quiz'], { state: { quiz } });
  }
}
