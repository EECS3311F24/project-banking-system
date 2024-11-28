import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service'; // Adjust path as needed
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  displayedColumns: string[] = ['date', 'amount', 'description']; // Add necessary columns
  transactions: any[] = [];
  dataSource = new MatTableDataSource(this.transactions);

  constructor(private backendService: BackendService) {}

  ngOnInit(): void {
    const accountNumber = localStorage.getItem('accountNumber'); // Retrieve the account number from storage
    if (accountNumber) {
      this.backendService.getTransactionHistory(accountNumber).subscribe({
        next: (response) => {
          console.log('Transaction History:', response);
          this.transactions = response; // Bind response to transactions
          this.dataSource.data = this.transactions; // Update Material Table data source
        },
        error: (err) => {
          console.error('Error fetching transaction history:', err);
        }
      });
    }
  }
}
