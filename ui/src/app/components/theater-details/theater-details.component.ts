import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SeatMapComponent } from '../seat-map/seat-map.component';
import { Seat } from '../../models/seat.model';
import { SessionStoreService } from '../../services/sessionstore.service';

@Component({
  selector: 'app-theater-details',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    SeatMapComponent
  ],
  templateUrl: './theater-details.component.html',
  styleUrls: ['./theater-details.component.css']
})
export class TheaterDetailsComponent implements OnInit {
  selctedMovie: any;
  theaters: any[] = [];
  selectedTheater: any = null;
  selectedShowTime: any = null;
  selectedSeats: { row: number; seat: number }[] = [];
  seats: Seat[] = [];
  selectedSeat: any;
  bookedTicket: any;
  seatMap: any[][] = []; // Dummy seat map

  constructor(
    private router: Router, 
    private http: HttpClient, 
    private sessionStoreService: SessionStoreService,
  ) {}

  ngOnInit(): void {
    this.selctedMovie = window.history.state.selectedMovie;
    this.fetchTheaters(this.selctedMovie.id); 
  }

  fetchTheaters(movieId: number): void {
    const apiUrl = `http://localhost:8080/api/theatres/by-movie?movieId=${movieId}`;
    this.http.get<any[]>(apiUrl).subscribe({
      next: (data) => {
        this.theaters = data;
        console.log('Fetched Theaters:', this.theaters);
      },
      error: (error) => {
        console.error('Error fetching theaters:', error);
      }
    });
  }

  selectTheater(theater: any): void {
    this.selectedTheater = {
      ...theater,
      showTimes: theater.showtimes.map((showtime: any) => `${showtime.date} ${showtime.time}`),
    };
    this.selectedShowTime = null;
    this.selectedSeats = [];
  }
  

  selectShowTime(time: any): void {
  this.selectedShowTime = time;
  this.selectedSeats = [];

  // Update the seatMap based on the selected showtime's data
  if (this.selectedTheater && this.selectedShowTime) {
    this.seats = this.selectedShowTime.seats;
  }
}
generateSeatMap(seats: any[]): string[][] {
  const rows: string[][] = [];
  const rowMap: { [key: string]: string[] } = {};

  seats.forEach((seat) => {
    const rowLabel = seat.rowLabel;
    if (!rowMap[rowLabel] ) {
      rowMap[rowLabel] = [];
    }
    rowMap[rowLabel].push(seat.reserved ? 'N' : 'A');
  });

  for (const rowLabel in rowMap) {
    rows.push(rowMap[rowLabel]);
  }

  return rows;
}


  toggleSeatSelection(rowIndex: number, seatIndex: number, seat: string): void {
    if (seat === 'A') {
      const seatAlreadySelected = this.isSeatSelected(rowIndex, seatIndex);
      if (seatAlreadySelected) {
        this.selectedSeats = this.selectedSeats.filter(
          (s) => s.row !== rowIndex || s.seat !== seatIndex
        );
      } else {
        this.selectedSeats.push({ row: rowIndex, seat: seatIndex });
      }
    }
  }

  isSeatSelected(rowIndex: number, seatIndex: number): boolean {
    return this.selectedSeats.some(
      (seat) => seat.row === rowIndex && seat.seat === seatIndex
    );
  }

  bookTicket(): void {
    console.log('Booking tickets for:', this.selectedSeat);

    this.router.navigate(['/payment'], 
      {
        state:{
          bookingDetails : {
            "seatId": this.selectedSeat.id,
            "showtimeId": this.selectedShowTime.id
          }
        }
      })
  }

  onSeatSelected(seat: any | null): void {
    this.selectedSeat = seat
    // Handle the selected seat ID here
  }
}
