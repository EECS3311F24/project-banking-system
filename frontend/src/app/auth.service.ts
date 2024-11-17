import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor() {}

  // Simulate login by saving a token in local storage
  login(token: string): void {
    localStorage.setItem('authToken', token);
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
