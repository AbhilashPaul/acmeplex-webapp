import { Component } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule, NgFor, MatCardModule], 
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent {
  movies = [
    { title: 'Movie 1', image: '/assets/Joker.jpg' },
    { title: 'Movie 2', image: '/assets/Titanic.jpg' },
    { title: 'Movie 3', image: '/assets/HarryPotter.jpg' }
  ];
}
