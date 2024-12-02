import { Component, Input } from '@angular/core';
import { Seat } from '../../models/seat.model';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-seat-map',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule],
  templateUrl: './seat-map.component.html',
  styleUrl: './seat-map.component.css'
})
export class SeatMapComponent {
  @Input() seats: Seat[] = [];
  selectedSeat: any;

  toggleReservation(seatId: number): void {
    const seat = this.seats.find(s => s.id === seatId);
    if (seat) {
      seat.reserved = !seat.reserved;
    }
  }
}
