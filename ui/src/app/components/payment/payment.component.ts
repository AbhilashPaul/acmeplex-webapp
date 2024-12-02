import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PaymentService } from '../../services/payment.service';
import { ReactiveFormsModule } from '@angular/forms'; // Import ReactiveFormsModule
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { SessionStoreService } from '../../services/sessionstore.service';

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
  customerEmail: any;
  customerName: any;

  constructor(
    private fb: FormBuilder, 
    private paymentService: PaymentService,
    private sessionStoreService: SessionStoreService,
  ) {}

  ngOnInit(): void {
    // Initialize the payment form
    this.bookingDetails = window.history.state.bookingDetails;
    this.seatID = this.bookingDetails.seatId;
    this.showTimeID = this.bookingDetails.showtimeId
    const user = this.sessionStoreService.getUser()
    if (user != null){
      this.isRegistered = true;
      this.customerName = user.firstName + ' ' + user.lastName;
      this.customerEmail = user.email;
    }

    this.paymentForm = this.fb.group({
      fullName: [this.customerName, [Validators.required, Validators.minLength(3)]],
      email: [this.customerEmail, [Validators.required, Validators.email]],
      cardHolderName: ['', [Validators.required]],
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{12,16}$/)]],
      expiryDate: ['', [Validators.required, Validators.pattern(/^(0[1-9]|1[0-2])\/\d{2}$/)]],
      cvv: ['', [Validators.required, Validators.pattern(/^\d{3}$/)]],
    });
    

    // Fetch ticket details
    this.fetchTicketDetails();
  }



  fetchTicketDetails(): void {
    const ticketPayload = {
      customerName: this.customerName || this.paymentForm.value.fullName,
      customerEmail: this.customerEmail || this.paymentForm.value.email,
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
