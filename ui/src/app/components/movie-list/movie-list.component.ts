import { Component } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { RouterModule,Router } from '@angular/router';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [CommonModule, MatCardModule,RouterModule,NgFor],
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css'],
})
export class MovieListComponent {
  movies = [
    {
      id: 1,
      title: 'Movie 1',
      description: 'A thought-provoking film about society and individual struggles.',
      duration: 120,
      genre: 'Drama, Thriller',
      movieRating: 8.5,
      imageUrl: '/assets/Joker.jpg',
    },
    {
      id: 2,
      title: 'Movie 2',
      description: 'A timeless love story set against the tragic sinking of the Titanic.',
      duration: 195,
      genre: 'Romance, Drama',
      movieRating: 7.9,
      imageUrl: '/assets/Titanic.jpg',
    },
    {
      id: 3,
      title: 'Movie 3',
      description:
        'A young wizard discovers his magical heritage and faces incredible adventures at Hogwarts.',
      duration: 152,
      genre: 'Fantasy, Adventure, Family',
      movieRating: 8.7,
      imageUrl: '/assets/HarryPotter.jpg',
    },
  ];

  constructor(private router: Router) {}

  viewMovieDetails(movie: any) {
    this.router.navigate(['/movie-details'], { state: { movie } });
  }
}

