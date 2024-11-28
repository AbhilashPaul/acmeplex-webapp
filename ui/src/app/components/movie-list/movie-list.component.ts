import { Component, OnInit } from '@angular/core';
import { MovieService, Movie } from '../../services/movie.service';
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
export class MovieListComponent implements OnInit {
  movies: Movie[] = []; // Movies fetched from the API

  constructor(private movieService: MovieService, private router: Router) {}

  ngOnInit(): void {
    this.fetchMovies();
  }

  // Fetch movies from the backend
  fetchMovies() {
    this.movieService.getMovies().subscribe({
      next: (data) => {
        console.log('Fetched movies:', data);
        this.movies = data;
      },
      error: (error) => {
        console.error('Error fetching movies:', error);
      },
      complete: () => {
        console.log('Movie fetching completed.');
      }
    });
  }
  

  // Navigate to movie details
  viewMovieDetails(movie: Movie) {
    this.router.navigate(['/movie-details'], { state: { movie } });
  }
}
