import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms'; 


@Component({
  standalone: true,
  imports: [ReactiveFormsModule],
  selector: 'app-cancel-ticket',
  templateUrl: './cancel-ticket.component.html',
  styleUrls: ['./cancel-ticket.component.css'],
})
export class CancelTicketComponent {
  searchForm: FormGroup;
  isLoggedIn = false; // Replace with actual login logic
  bookedTickets = [
    { id: 1, movie: 'Movie 1', date: '2024-12-02', seat: 'A1' },
    { id: 2, movie: 'Movie 2', date: '2024-12-03', seat: 'B3' },
  ]; // Replace with real data
  searchResults: any[] = [];

  constructor(private fb: FormBuilder) {
    this.searchForm = this.fb.group({
      email: [''],
    });
  }

  searchTickets() {
    const email = this.searchForm.value.email;
    // Perform search logic here
    this.searchResults = [
      { id: 3, movie: 'Movie 3', date: '2024-12-04', seat: 'C5' },
    ]; // Replace with API data
  }

  cancelTicket(ticketId: number) {
    // Implement cancellation logic here
    alert(`Ticket with ID ${ticketId} has been canceled.`);
    // Remove the ticket from bookedTickets or searchResults
    this.bookedTickets = this.bookedTickets.filter((t) => t.id !== ticketId);
    this.searchResults = this.searchResults.filter((t) => t.id !== ticketId);
  }
}

