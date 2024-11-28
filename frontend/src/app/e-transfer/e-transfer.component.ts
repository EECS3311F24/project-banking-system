import { FormBuilder, FormGroup } from '@angular/forms';
import { BackendService } from '../services/backend.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-e-transfer',
  templateUrl: './e-transfer.component.html',
  styleUrl: './e-transfer.component.css'
})
export class ETransferComponent {
  transferForm: FormGroup;

  constructor (private fb: FormBuilder, private backendService: BackendService){
    this.transferForm = this.fb.group({
      fromEmail: [''],
      toEmail: [''],
      amount: [0],
    });
  }

  onTransfer() {
    const transferData = this.transferForm.value;
    console.log('Transfer Data: ', transferData);
  
    this.backendService.transferFunds(transferData).subscribe({
      next: (response) => {
        console.log('Transfer successful', response);
        alert(response); // Alert the plain text response
      },
      error: (err) => {
        console.error('Error during transfer:', err);
        if (err.error) {
          alert(`Transfer failed: ${err.error}`);
        } else {
          alert('An unknown error occurred during transfer.');
        }
      }
    });
  }
  
  

}
