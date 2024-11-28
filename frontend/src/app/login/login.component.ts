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
        // Store accountNumber and balance in localStorage for further use
        localStorage.setItem('accountNumber', response.accountNumber);
        localStorage.setItem('balance', response.balance.toString());
        localStorage.setItem('userName', response.name);

        // Navigate to the dashboard
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Login failed:', error);
        this.errorMessage = error.error.error || 'An error occurred during login.';
      },
    });
    // Normally, you would validate credentials here
   // this.authService.login('sampleToken');  // Mock login
    // this.router.navigate(['/dashboard']);
  }
}
