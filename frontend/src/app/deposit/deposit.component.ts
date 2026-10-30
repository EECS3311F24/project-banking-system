import { Component } from '@angular/core';
import { BackendService } from '../services/backend.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrl: './deposit.component.css'
})
export class DepositComponent {
  depositForm: FormGroup;

  constructor(private backendService: BackendService, private fb: FormBuilder) {
    this.depositForm = this.fb.group({
      accountNumber: ['', Validators.required],
      amount: [0, [Validators.required, Validators.min(1)]]
    });
  }

  onDeposit(): void {
    if (this.depositForm.valid) {
      const { accountNumber, amount } = this.depositForm.value;

      this.backendService.deposit(accountNumber, amount).subscribe({
        next: (response) => {
          alert("Your funds have been succesfully deposited"); // Inform the user of success
        },
        error: (err) => {
          console.error('Error during deposit:', err);
          alert('Failed to deposit. Please try again.');
        }
      });
    }
  }

}
