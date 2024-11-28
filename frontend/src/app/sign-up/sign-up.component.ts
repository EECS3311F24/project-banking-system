import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  name: string = '';
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private backendService: BackendService, private router: Router) {}

  onSignup(): void {
    const user = { name: this.name, email: this.email, password: this.password };
    this.backendService.signup(user).subscribe({
      next: (response) => {
        console.log('Signup successful:', response);
        alert('Signup successful! Your account has been created.');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('Signup failed:', err);
        this.errorMessage = 'Signup failed. Please try again.';
      },
    });
  }
}
