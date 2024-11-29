import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { TheaterService, Theater } from '../../services/theater.service';

@Component({
  selector: 'app-theater-details',
  standalone: true,
  imports: [CommonModule, FormsModule, MatCardModule, MatButtonModule],
  templateUrl: './theater-details.component.html',
  styleUrls: ['./theater-details.component.css'],
})
export class TheaterDetailsComponent implements OnInit {
  theaters: Theater[] = [];
  selectedTheater: Theater | null = null;

  constructor(private theaterService: TheaterService, private router: Router) {}

  ngOnInit(): void {
    this.fetchTheaters();
  }

  fetchTheaters(): void {
    this.theaterService.getTheaters().subscribe({
      next: (data) => {
        console.log('Fetched theaters:', data);
        this.theaters = data;
      },
      error: (error) => {
        console.error('Error fetching theaters:', error);
      },
    });
  }

  selectTheater(theater: Theater): void {
    this.selectedTheater = theater;
  }
}
