import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {
  withdrawForm: FormGroup;

  constructor(private fb: FormBuilder, private backendService: BackendService) {
    this.withdrawForm = this.fb.group({
      accountNumber: ['', Validators.required],
      amount: ['', [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit(): void {
  }

  onWithdraw(): void {
    if (this.withdrawForm.valid) {
      const { accountNumber, amount } = this.withdrawForm.value;

      this.backendService.withdraw(accountNumber, amount).subscribe({
        next: (response) => {
          alert('Withdrawal successful!');
          this.withdrawForm.reset();
        },
        error: (err) => {
          console.error('Error during withdrawal:', err);
          alert('An error occurred. Please try again.');
        }
      });
    } else {
      alert('Please fill out the form correctly.');
    }
  }
}
