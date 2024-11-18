import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.css'
})
export class TransactionsComponent {
  displayedColumns: string[] = ['date', 'amount'];
  transactions = [
    { date: '2024-11-15', amount: '$200' },
    { date: '2024-11-14', amount: '$500' },
    { date: '2024-11-13', amount: '$150' }
  ];
  dataSource = new MatTableDataSource(this.transactions);

  constructor(private router: Router) {}

  navigateToDashboard(): void {
    this.router.navigate(['/dashboard']); // Adjust route as needed
  }
  

}
