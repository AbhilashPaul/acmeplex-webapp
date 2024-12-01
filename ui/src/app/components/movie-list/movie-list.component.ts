import { Component, OnInit } from '@angular/core';
import { MovieService, Movie } from '../../services/movie.service';
import { CommonModule, NgFor } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-movie-list',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    RouterModule,
    FormsModule,
    NgFor,
    MatIconModule
  ],
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css'],
})
export class MovieListComponent implements OnInit {
  movies: Movie[] = []; // Movies fetched from the API
  searchQuery: string = ''; // User's search query

  constructor(private movieService: MovieService, private router: Router) {}

  ngOnInit(): void {
    this.fetchMovies();
  }

  // Fetch all movies
  fetchMovies() {
    this.movieService.getMovies().subscribe({
      next: (data) => {
        this.movies = data;
      },
      error: (error) => {
        console.error('Error fetching movies:', error);
      },
    });
  }

  // Search for movies
  searchMovies() {
    if (this.searchQuery.trim() === '') {
      this.fetchMovies(); // If the search query is empty, fetch all movies
    } else {
      this.movieService.searchMovies(this.searchQuery).subscribe({
        next: (data) => {
          this.movies = data;
        },
        error: (error) => {
          console.error('Error searching movies:', error);
        },
      });
    }
  }

  // Navigate to movie details
  viewMovieDetails(movie: Movie) {
    this.router.navigate(['/movie-details'], { state: { movie } });
  }
}
