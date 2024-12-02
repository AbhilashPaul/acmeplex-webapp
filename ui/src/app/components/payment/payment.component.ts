import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms'; // <-- Import ReactiveFormsModule here
import { RouterOutlet } from '@angular/router';
import { PaymentService } from '../../services/payment.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-payment',
  standalone: true,  // <-- Standalone component flag
  imports: [RouterOutlet, ReactiveFormsModule, CommonModule],  // <-- Add ReactiveFormsModule here
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent  implements OnInit {
  paymentForm: FormGroup;
  isSubmitted = false;
  isRegistered = false;
  confirmationMessage = '';
  ticketDetails: any = null;
  bookingDetails: any = null;

  ngOnInit(): void {
    this.bookingDetails = window.history.state.bookingDetails;
    console.log(this.bookingDetails)
  }

  constructor(private fb: FormBuilder, private paymentService: PaymentService) {
    // Initialize the form group
    this.paymentForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      cardHolderName: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      cardNumber: ['', [Validators.required,Validators.pattern(/^\d{16}$/)]],
      expiryDate: ['', [Validators.required, Validators.pattern(/^(0[1-9]|1[0-2])\/\d{2}$/)]],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3}$/)]],
    });
  }

  get formControls() {
    return this.paymentForm.controls;
  }

  onSubmit() {
    this.isSubmitted = true;

    if (this.paymentForm.valid) {
      const paymentData = this.paymentForm.value;

      // Make payment
      this.paymentService.makePayment(paymentData).subscribe({
        next: (response) => {
          this.confirmationMessage = 'Payment Successful!';
          this.ticketDetails = response
        },
        error: (err) => {
          console.error('Payment failed:', err);
          this.confirmationMessage = 'Payment Failed! Please try again.';
        }
      });
    }
  }
}
