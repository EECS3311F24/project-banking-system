import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { HttpClient } from '@angular/common/http';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private backendService: BackendService, private router: Router) {}

  onLogin(): void {
    this.backendService.login(this.email, this.password).subscribe({
      next: (response) => {
        console.log(response);
  
        // Store account details in local storage
        localStorage.setItem('accountNumber', response.accountNumber);
        localStorage.setItem('balance', response.balance.toString());
        localStorage.setItem('userName', response.name);
  
        // Navigate to the dashboard
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Login failed:', error);
  
        // Extract and display error message
        if (error.error && typeof error.error === 'string') {
          this.errorMessage = error.error; // Backend returned a plain text message
        } else if (error.error && error.error.message) {
          this.errorMessage = error.error.message; // Backend returned an error object with a message property
        } else {
          this.errorMessage = 'An unexpected error occurred during login.';
        }
      },
    });
  }
  
}

