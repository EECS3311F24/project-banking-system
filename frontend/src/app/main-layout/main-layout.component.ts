import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.css'
})
export class MainLayoutComponent implements OnInit{
  isDashboard: boolean = true;
  isLoginPage: boolean = false;
  userName: string = '';
   currentTitle: string = 'Welcome to Dashboard'

  constructor(private router: Router) {
    
  }

  ngOnInit(): void {

    const fullName = localStorage.getItem('userName') || '';
    this.userName = fullName.split(' ')[0]; // Use only the first name
    this.router.events
    .pipe(filter((event) => event instanceof NavigationEnd))
    .subscribe((event: NavigationEnd) => {
      this.setPageTitle(this.router.url);
    });
  }

  setPageTitle(route: string): void {
    // Match the current route and set the appropriate title
    if (route.includes('/dashboard')) {
      this.currentTitle = 'Welcome to Dashboard';
    }
    if (route.includes('/dashboard/deposit')) {
      this.currentTitle = 'Make a Deposit';
    } else if (route.includes('/dashboard/withdraw')) {
      this.currentTitle = 'Withdraw Funds';
    } else if (route.includes('/dashboard/e-transfer')) {
      this.currentTitle = 'Send Money';
    } else if (route.includes('/dashboard/transactions')) {
      this.currentTitle = 'Transaction History';
    } else if (route.includes('/settings')) {
      this.currentTitle = 'Account Settings';
    } else if (route.includes('/contact')) {
      this.currentTitle = 'Contact Support';
    } else if (route.includes('/dashboard/loan-management')) {
      this.currentTitle = 'Welcome to Loan Eligiblity Calculator';
    } else {
      this.currentTitle = 'Welcome to Dashboard'; // Fallback
    }
  }

  onLogout(): void {
    localStorage.removeItem('userName');
    this.router.navigate(['/login']);
  }
}
