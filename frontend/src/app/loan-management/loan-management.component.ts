import { Component } from '@angular/core';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-loan-management',
  templateUrl: './loan-management.component.html',
  styleUrls: ['./loan-management.component.css']
})
export class LoanManagementComponent {
  monthlyDebts: number = 0;
  monthlyIncome: number = 0;
  creditScore: number = 0;
  accountNumber: string = '';
  loanResult: string | null = null;

  constructor(private backendService: BackendService) {}

  checkLoanEligibility() {
    this.backendService.getAccountDetails(this.accountNumber).subscribe(
      (response: { accountNumber: string; balance: number }) => {
        const { balance } = response;
        const dtiRatio = (this.monthlyDebts / this.monthlyIncome) * 100; // Calculate DTI ratio

        if (dtiRatio < 40 && this.creditScore >= 600 && balance >= 5000) {
          // Case 1: DTI < 40, decent credit score, and sufficient balance
          this.loanResult = 'You are eligible for a loan!';
        } else if (
          dtiRatio >= 40 &&
          dtiRatio <= 50 &&
          this.creditScore >= 750 &&
          balance >= 10000
        ) {
          // Case 2: DTI between 40-50, high credit score, and high balance
          this.loanResult = 'You are eligible for a loan!';
        } else {
          // Case 3: Does not meet any criteria
          this.loanResult = 'Sorry, you are not eligible for a loan.';
        }
      },
      (error) => {
        this.loanResult = 'Error fetching account details.';
        console.error(error);
      }
    );
  }
}
