import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import baseUrl from './helper';


@Injectable({
  providedIn: 'root'
})
export class QuizService {
  
  apiUrl = '/proxy/api.php'; 
  queryParams: any; // Define queryParams property
  
  Constructor() {
    // this.queryParams = {
    //   amount: 10, // number of questions
    //   category: 18, // category ID (https://opentdb.com/api_category.php)
    //   difficulty: 'easy', // question difficulty
    //   type: 'multiple' // question type (multiple choice, true/false, etc.)
    // };
  }
  
  constructor(private http: HttpClient) { }
  
  getQuizQuestions(queryParams: any): Observable<any> {
    const params = new HttpParams({
      fromObject: queryParams
    });
    return this.http.get(`${baseUrl}`+ this.apiUrl, {params});
    
  }
 

} 
