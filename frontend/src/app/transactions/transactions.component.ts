import { Component, OnInit } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {
  displayedColumns: string[] = ['date', 'amount', 'description'];
  transactions: any[] = [];
  dataSource = new MatTableDataSource(this.transactions);
  transactionTypes: string[] = ['Deposit', 'Withdrawal', 'Transfer', 'All']; // Define transaction types
  filterCriteria = {
    type: 'All',
    date: null
  };

  constructor(private backendService: BackendService) {}

  ngOnInit(): void {
    const accountNumber = localStorage.getItem('accountNumber'); // Retrieve account number from storage
    if (accountNumber) {
      this.backendService.getTransactionHistory(accountNumber).subscribe({
        next: (response) => {
          console.log('Transaction History:', response);
          this.transactions = response;
          this.dataSource.data = this.transactions; // Update the table data
        },
        error: (err) => {
          console.error('Error fetching transaction history:', err);
        }
      });
    }
  }

  applyTypeFilter(event: any): void {
    const selectedType = event.value;
    this.filterCriteria.type = selectedType;
    this.applyFilters();
  }

  applyDateFilter(event: any): void {
    const selectedDate = event.value; // Get the selected date
    this.filterCriteria.date = selectedDate;
    this.applyFilters();
  }

  applyFilters(): void {
    let filteredData = this.transactions;

    // Filter by transaction type
    if (this.filterCriteria.type && this.filterCriteria.type !== 'All') {
      filteredData = filteredData.filter(
        (transaction) => transaction.type === this.filterCriteria.type
      );
    }

    // Filter by date
    if (this.filterCriteria.date) {
      const selectedDate = new Date(this.filterCriteria.date).toDateString();
      filteredData = filteredData.filter(
        (transaction) =>
          new Date(transaction.timestamp).toDateString() === selectedDate
      );
    }

    this.dataSource.data = filteredData; // Update the table data source
  }
}
