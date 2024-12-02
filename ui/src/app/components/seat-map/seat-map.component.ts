import { Component, Input, Output, EventEmitter } from '@angular/core';
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
  @Output() seatSelected = new EventEmitter<number>();
  selectedSeat: any;

  toggleSeatSelection(seatId: number): void {
    const seat = this.seats.find(s => s.id === seatId);
    if (seat) {
      if (this.selectedSeat === seat) {
        this.selectedSeat = null;
      } else {
        this.selectedSeat = seat;
      }
      console.log(this.selectedSeat)
      this.seatSelected.emit(this.selectedSeat);
    }
  }
}
