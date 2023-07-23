import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-userhome',
  templateUrl: './userhome.component.html',
  styleUrls: ['./userhome.component.css']
})
export class UserhomeComponent {
  
  quizData: any;
  // Initializing the queryParams property as an object (empty object {} in this case) ensures that the property exists and has a valid object reference.
  queryParams: any = {};
  selectedOptions: any[] = [];
  constructor(
    private quizService: QuizService
  ) { }
  onSubmit(formData: any) {
    console.log(formData);

    const queryParams = {
      amount: formData.numberOfQuestions,
      category: formData.category,
      difficulty: formData.difficulty,
      type: formData.type
    };
  
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
    // Implement your logic to submit the quiz
  }
}