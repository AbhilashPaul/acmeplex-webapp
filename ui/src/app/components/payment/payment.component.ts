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

  constructor(private fb: FormBuilder) {
    // Initialize the form group
    this.paymentForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      paymentCard: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.paymentForm.valid) {
      // Handle form submission
      console.log(this.paymentForm.value);
    }
  }
}
