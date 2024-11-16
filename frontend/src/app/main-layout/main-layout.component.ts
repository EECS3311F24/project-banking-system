import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css'
})
export class MainLayoutComponent {
  isDashboard: boolean = true;
  isLoginPage: boolean = false;

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Update isDashboard only for dashboard route
        this.isDashboard = event.urlAfterRedirects === '/dashboard';

        // Check if the current route is '/login' for login page display control
        this.isLoginPage = event.urlAfterRedirects === '/login';
      }
    });
  }
  onLogout() {
    // Navigate to login page on logout
    this.router.navigate(['/login']);
  }
}
