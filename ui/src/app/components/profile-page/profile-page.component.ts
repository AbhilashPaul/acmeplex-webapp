import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SessionStoreService } from '../../services/sessionstore.service';
import { HttpClient } from '@angular/common/http';

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
export class ProfilePageComponent implements OnInit {
  userData = {
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    address: '',
    memberSince:'',
    annualFeePaid:false,
    membershipExpired:false,
  };

  credits = 0; 
  constructor(
    private router: Router, 
    private snackBar: MatSnackBar, 
    private sessionStoreService: SessionStoreService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.getUserProfile();  // Fetch user profile on component load
  }

  getUserProfile() {
    const userData = this.sessionStoreService.getUser();

    if (!userData) {
      this.snackBar.open('No user session found. Please log in again.', 'Close', {
        duration: 3000,
      });
      this.router.navigate(['/login']);
      return;
    }

    this.userData = {
      firstName: userData.firstName,
      lastName: userData.lastName,
      email: userData.email,
      phoneNumber: userData.phoneNumber,
      address: userData.address,
      memberSince: userData.memberSince,
      annualFeePaid: userData.annualFeePaid,
      membershipExpired: userData.membershipExpired,
    };
  }
  
  onLogout() {
    this.sessionStoreService.clearUser();
    this.snackBar.open('You have been logged out.', 'Close', {
      duration: 3000,
    });
    this.router.navigate(['/login']); 
  }
  payAnnualFee(){
    this.router.navigate(['/annual-payment']); 
  }

  

  // cancelTicket(ticket: any) {
  //   this.bookedTickets = this.bookedTickets.filter((t) => t.id !== ticket.id);
  //   this.credits += 10; 
  //   this.snackBar.open(`Ticket for ${ticket.movie}, Seat ${ticket.seat} cancelled. Credit added!`, 'Close', {
  //     duration: 3000,
  //     panelClass: 'success-snackbar',
  //   });
  // }
}