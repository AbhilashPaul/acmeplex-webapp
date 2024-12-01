import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms'; // <-- Import ReactiveFormsModule here
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-payment',
  standalone: true,  // <-- Standalone component flag
  imports: [RouterOutlet, ReactiveFormsModule],  // <-- Add ReactiveFormsModule here
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent {
  paymentForm: FormGroup;
  isSubmitted = false;
  isRegistered = false;

  constructor(private fb: FormBuilder) {
    // Initialize the form group
    this.paymentForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
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
      // Handle form submission
      console.log(this.paymentForm.value);
    }
  }
}
