// 

import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PaymentService } from '../../services/payment.service';
import { ReactiveFormsModule } from '@angular/forms'; // Import ReactiveFormsModule
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-payment',
  standalone: true,
  templateUrl: './payment.component.html',
  imports: [ReactiveFormsModule, CommonModule, RouterOutlet],
  styleUrls: ['./payment.component.css'],
})
export class PaymentComponent implements OnInit {
  paymentForm!: FormGroup;
  isSubmitted = false;
  isRegistered = false;
  confirmationMessage = '';
  ticketDetails: any;
  ticketPrice: any;
  bookingDetails: any = null;
  seatID: any;
  showTimeID: any;
  constructor(private fb: FormBuilder, private paymentService: PaymentService) {}

  ngOnInit(): void {
    // Initialize the payment form
    this.paymentForm = this.fb.group({
      fullName: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      cardHolderName: ['', [Validators.required]],
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{12,16}$/)]],
      expiryDate: ['', [Validators.required, Validators.pattern(/^(0[1-9]|1[0-2])\/\d{2}$/)]],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3}$/)]],
    });
    this.bookingDetails = window.history.state.bookingDetails;
    this.seatID = this.bookingDetails.seatId;
    this.showTimeID = this.bookingDetails.showtimeId
    // Fetch ticket details
    this.fetchTicketDetails();
  }



  fetchTicketDetails(): void {
    const ticketPayload = {
      customerName: this.paymentForm.value.fullName || 'Default Name',
      customerEmail: this.paymentForm.value.email || 'default@example.com',
      seatId: this.seatID,
      showtimeId: this.showTimeID,
    };

    this.paymentService.getTicketDetails(ticketPayload).subscribe({
      next: (ticket) => {
        this.ticketDetails = ticket;
        this.ticketPrice = ticket.price || 0;
        console.log('Ticket details fetched:', this.ticketDetails);
      },
      error: (err) => {
        console.error('Failed to fetch ticket details:', err);
        this.confirmationMessage = 'Failed to fetch ticket details. Please try again.';
      },
    });
  }

  onSubmit(): void {
    this.isSubmitted = true;

    if (this.paymentForm.invalid) {
      console.error('Invalid payment form:', this.paymentForm.value);
      return;
    }

    // Prepare the payment payload
    const paymentPayload = {
      customerName: this.paymentForm.value.fullName,
      customerEmail: this.paymentForm.value.email,
      ticketId: this.ticketDetails?.id,
      amount: this.ticketPrice,
      paymentCard: {
        cardNumber: this.paymentForm.value.cardNumber,
        cardHolderName: this.paymentForm.value.cardHolderName,
        expiryDate: this.paymentForm.value.expiryDate,
        cvv: this.paymentForm.value.cvv,
      },
    };
    console.log("payload", paymentPayload)

    this.paymentService.makePayment(paymentPayload).subscribe({
      next: (response) => {
        console.log('Payment successful:', response);
        this.confirmationMessage = 'Payment Successful! Your ticket has been booked.';
      },
      error: (err) => {
        console.error('Payment failed:', err);
        console.error('Full error details:', err)
        this.confirmationMessage = 'Payment Failed! Please try again.';
      },
    });
  }

  get formControls() {
    return this.paymentForm.controls;
  }
}
