import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css'
})
export class MainLayoutComponent implements OnInit{
  isDashboard: boolean = true;
  isLoginPage: boolean = false;
  userName: string = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    // this.router.events.subscribe((event) => {
    //   if (event instanceof NavigationEnd) {
    //     // Update isDashboard only for dashboard route
    //     this.isDashboard = event.urlAfterRedirects === '/dashboard';

    //     // Check if the current route is '/login' for login page display control
    //     this.isLoginPage = event.urlAfterRedirects === '/login';
    //   }
    // });
    this.userName = localStorage.getItem('userName') || 'User';
  }
  onLogout(): void {
    localStorage.removeItem('userName');
    this.router.navigate(['/login']);
  }
}
