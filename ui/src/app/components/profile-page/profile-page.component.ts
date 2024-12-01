import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SessionStoreService } from '../../services/sessionstore.service';

@Component({
  selector: 'app-profile-page',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
  ],
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css'],
})
export class ProfilePageComponent {
  user = {
    firstName: 'John',
    lastName: 'Doe',
    email: 'johndoe@example.com',
    phoneNumber: '123-456-7890',
    address: '123 Main St, Anytown, USA',
  };

  credits = 0; 
  bookedTickets = [
    { id: 1, movie: 'Harry Potter', date: '2024-12-01', seat: 'A1' },
    { id: 2, movie: 'Inception', date: '2024-12-05', seat: 'B3' },
  ]; 
   
  constructor(
    private router: Router, 
    private snackBar: MatSnackBar, 
    private sessionStoreService: SessionStoreService,
  ) {}

  onLogout() {
    this.sessionStoreService.clearUser();
    this.snackBar.open('You have been logged out.', 'Close', {
      duration: 3000,
    });
    this.router.navigate(['']); 
  }

  cancelTicket(ticket: any) {
    this.bookedTickets = this.bookedTickets.filter((t) => t.id !== ticket.id);
    this.credits += 10; 
    this.snackBar.open(`Ticket for ${ticket.movie}, Seat ${ticket.seat} cancelled. Credit added!`, 'Close', {
      duration: 3000,
      panelClass: 'success-snackbar',
    });
  }  
}