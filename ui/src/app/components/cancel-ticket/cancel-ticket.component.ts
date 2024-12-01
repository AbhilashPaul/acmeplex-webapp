import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatCardTitle } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core'; 
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  standalone: true,
  imports: [ReactiveFormsModule,MatButtonModule,MatCardModule, MatCardTitle, MatFormFieldModule, MatInputModule, CommonModule
  ],
  selector: 'app-cancel-ticket',
  templateUrl: './cancel-ticket.component.html',
  styleUrls: ['./cancel-ticket.component.css'],
})
export class CancelTicketComponent {
  searchForm: FormGroup;
  // isLoggedIn = false;
  searchAttempted = false;
  searchResults: any[] = [];
  cancellationMessage: string = ''; 

  constructor(private fb: FormBuilder, private http: HttpClient, private cdRef: ChangeDetectorRef, private snackBar: MatSnackBar) {
    this.searchForm = this.fb.group({
      email: [''],
    });
  }

  searchTickets() {
    const email = this.searchForm.get('email')?.value;
    const searchUrl = `http://localhost:8080/api/tickets/by-email?email=${email}`;
    console.log('Searching for tickets with email:', email);
    this.http.get<any[]>(searchUrl).subscribe(
      (response) => {
        console.log('Search response:', response); 
        this.searchResults = response;
        this.searchAttempted = true;
      },
      (error) => {
        console.error('Error fetching tickets:', error);
        this.searchResults = [];
        this.searchAttempted = true;
      }
    );
  }

  cancelTicket(ticketId: number) {
    const cancelUrl = `http://localhost:8080/api/tickets/${ticketId}/cancel`;
    
    this.http.post(cancelUrl, {}).subscribe(
      (response) => {
        alert(`Ticket ID ${ticketId} cancelled successfully!`);
        this.cancellationMessage = "Ticket cancelled successfully! You gained a credit, which will be automatically applied to your next purchase.";
        
        // Show snack bar with the message
        this.snackBar.open(this.cancellationMessage, 'Close', { duration: 5000 });
        // Remove the cancelled ticket from search results
        this.searchResults = this.searchResults.filter(ticket => ticket.id !== ticketId);
        // this.searchTickets();
        this.cdRef.detectChanges();
      },
      (error) => {
        console.error('Error cancelling ticket:', error);
        alert('Failed to cancel the ticket.');
      }
    );
  }
}
