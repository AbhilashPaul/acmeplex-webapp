// import { Film, Clock, MapPin, Star, Calendar, Ticket } from 'lucide-react';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { NgModule } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-theater-details',
  standalone: true,
  imports: [
    CommonModule, 
    FormsModule,
    MatCardModule,
    MatButtonModule,
  ],
  templateUrl: './theater-details.component.html',
  styleUrl: './theater-details.component.css'
})

export class TheaterDetailsComponent {
  constructor(private router: Router){}
  theaters = [
    { name: 'Theater 1', address: '123 Main St', showTimes: ['10:00 AM', '1:00 PM', '6:00 PM'] },
    { name: 'Theater 2', address: '456 Elm St', showTimes: ['11:00 AM', '2:00 PM', '7:00 PM'] },
    { name: 'Theater 3', address: '789 Oak St', showTimes: ['12:00 PM', '4:00 PM', '8:00 PM'] },
  ];

  seatMap = [
    ['A', 'A', 'A', 'N', 'N', 'A', 'A'],
    ['A', 'A', 'N', 'N', 'A', 'A', 'A'],
    ['A', 'N', 'N', 'A', 'A', 'A', 'N'],
    ['A', 'A', 'A', 'A', 'N', 'N', 'A'],
  ];

  selectedTheater: any = null;
  selectedSeats: { row: number; seat: number }[] = [];
  showTimes = ['10:00 AM', '1:00 PM', '6:00 PM']; // Example showtimes
  selectedShowTime: string | null = null;
  // seatMap: string[][] = [];

  selectTheater(theater: any): void {
    this.selectedTheater = theater;
    this.selectedShowTime = null;
    // this.seatMap = theater.seatMap;
    this.selectedSeats = [];
  }

  toggleSeatSelection(rowIndex: number, seatIndex: number, seat: string): void {
    // Allow selection only if the seat is available
    if (seat === 'A') {
      if (this.selectedSeats.length > 0) {
        this.selectedSeats = [];
      }
      this.selectedSeats.push({ row: rowIndex, seat: seatIndex });
    }
  }

  isSeatSelected(rowIndex: number, seatIndex: number): boolean {
    return this.selectedSeats.some(
      (seat) => seat.row === rowIndex && seat.seat === seatIndex
    );
  }

  selectShowTime(time: string): void {
    this.selectedShowTime = time; // Set selected showtime
    this.selectedSeats = []; // Reset selected seats when showtime changes
  }

  bookTicket(){
    if (this.selectedSeats.length === 1) {
      const selectedSeat = this.selectedSeats[0];
      this.router.navigate(['/payment'])
      // You can add further functionality here, such as calling an API or navigating to a confirmation page
    } else {
      alert('Please select a seat before booking!');
    }
  }
}