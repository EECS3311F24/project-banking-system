import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
  accountNumber: string = '';
  balance: number = 0.0;
  recentTransaction: { description: string; amount: number; timestamp: string } | null = null;

  constructor(private backendService: BackendService) {} 

  ngOnInit(): void {
    // Assuming the account number is stored in local storage
    const accountNumber = localStorage.getItem('accountNumber') || '';
    console.log('Stored Account Number:', accountNumber);
    if (accountNumber) {
      this.backendService.getAccountDetails(accountNumber).subscribe({
        next: (response) => {
          console.log('API Response:', response);
          this.accountNumber = response.accountNumber;
          this.balance = response.balance;
        },
        error: (err) => {
          console.error('Error fetching account details:', err);
        }
      });
    }

    this.backendService.getRecentTransaction(accountNumber).subscribe({
      next: (transaction) => {
        console.log('Recent Transaction:', transaction);
        this.recentTransaction = {
          description: transaction.description,
          amount: transaction.amount,
          timestamp: transaction.timestamp
        };
      },
      error: (err) => {
        console.error('Error fetching recent transaction:', err);
      }
    });
  }
  

}