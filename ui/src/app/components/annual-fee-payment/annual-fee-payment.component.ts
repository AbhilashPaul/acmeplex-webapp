import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms'; // Import ReactiveFormsModule
import { Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { SessionStoreService } from '../../services/sessionstore.service';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-annual-fee-payment',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './annual-fee-payment.component.html',
  styleUrls: ['./annual-fee-payment.component.css'],
})
export class AnnualFeePaymentComponent {

  paymentForm: FormGroup;
  confirmationMessage = '';
  userDetails = {
    firstName: '',
    lastName: '',
    email: '',
    address: '',
    phoneNumber: '',
    password: '',
  };
  receipt:any;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router,
    private sessionStoreService: SessionStoreService,
    private http: HttpClient, 
) {
    this.paymentForm = this.fb.group({
      cardHolderName: ['', [Validators.required]],
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      expiryDate: ['', [Validators.required, Validators.pattern(/^(0[1-9]|1[0-2])\/\d{2}$/)]],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3}$/)]],
    });
  }

  // Getter for form controls
  get formControls() {
    return this.paymentForm.controls;
  }

  onSubmit() {
    if (this.paymentForm.valid) {
      const user=this.sessionStoreService.getUser();
      const paymentPayload = {
        userId: user.id,
        paymentCard: {
          cardNumber: this.paymentForm.value.cardNumber,
          cardHolderName: this.paymentForm.value.cardHolderName,
          expiryDate: this.paymentForm.value.expiryDate,
          cvv: this.paymentForm.value.cvv,
        },
      };
      const apiUrl = `http://localhost:8080/api/payments/annualFee`;
    this.http.post<any>(apiUrl,paymentPayload).subscribe({
      next: (data) => {
        this.receipt = data;
        console.log('Annual Fee payment receipt:', this.receipt);
        this.confirmationMessage = "Successfully paid the annual Fee. Email has benen sent to you with receipt details. Receipt Id: " + this.receipt.id + "Transaction Id: " + this.receipt.transactionId;
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 3000);
      },
      error: (error) => {
        console.error('Error paying annual fee:', error);
        this.confirmationMessage =  'Error while paying annual fee'
      }
    });
    }
  }
}

