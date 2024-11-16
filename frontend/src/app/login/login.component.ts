import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    // Normally, you would validate credentials here
    this.authService.login('sampleToken');  // Mock login
    this.router.navigate(['/dashboard']);
  }
}
