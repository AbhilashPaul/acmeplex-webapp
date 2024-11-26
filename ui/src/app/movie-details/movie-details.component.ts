import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [],
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {
  movieTitle!: string;
  movie: any;

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    // Extract movie title from the route parameter
    this.movieTitle = this.route.snapshot.paramMap.get('title')!;
    
    // Fetch movie details (replace with a service call in real scenarios)
    this.fetchMovieDetails();
  }

  fetchMovieDetails() {
    const movies = [
      {
        title: 'Movie 1',
        releaseYear: 2019,
        genre: 'Drama, Thriller',
        rating: 8.5,
        description: 'A thought-provoking film about society and individual struggles.',
        posterUrl: '/assets/Joker.jpg',
      },
      {
        title: 'Movie 2',
        releaseYear: 1997,
        genre: 'Romance, Drama',
        rating: 7.9,
        description: 'A timeless love story set against the tragic sinking of the Titanic.',
        posterUrl: '/assets/Titanic.jpg',
      },
      {
        title: 'Movie 3',
        releaseYear: 2001,
        genre: 'Fantasy, Adventure, Family',
        rating: 8.7,
        description:
          'A young wizard discovers his magical heritage and faces incredible adventures at Hogwarts.',
        posterUrl: '/assets/HarryPotter.jpg',
      },
    ];

    // Match the title and fetch movie details
    this.movie = movies.find((m) => m.title === this.movieTitle);
  }

  getTicket() {
    console.log('Ticket button clicked!');
    this.router.navigate(['/theater-details'])
  }
}
