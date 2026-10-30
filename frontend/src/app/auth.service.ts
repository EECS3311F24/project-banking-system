import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseurl = 'http://localhost:8080/api/customerInfo';

  constructor(private http: HttpClient) {}


  
  // Simulate login by saving a token in local storage
  login(token: string): void {
    localStorage.setItem('authToken', token);
    this.http.request(
      'get', 
      'https://localhost:8080/api/customerInfo', 
      {
      body: "customerMail: test@test.com, customerPass: testPass"
      }
    )
  }

  // Check if the user is logged in
  isLoggedIn(): boolean {
    return !!localStorage.getItem('authToken');
  }

  // Simulate logout by removing the token
  logout(): void {
    localStorage.removeItem('authToken');
  }
}
