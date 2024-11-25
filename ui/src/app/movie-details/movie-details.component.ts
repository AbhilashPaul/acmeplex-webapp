import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [],
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {
  movie = {
    title: 'The Matrix',
    releaseYear: 1999,
    genre: 'Science Fiction',
    rating: 8.7,
    
    description: 'A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.',
    posterUrl: 'assets/images/the-matrix.png',
    cast: ['Keanu Reeves', 'Laurence Fishburne', 'Carrie-Anne Moss']
  };

  ngOnInit(): void {
    console.log(this.movie); // Verify that movie data is available
  }
  getTicket(): void {
    console.log('Ticket button clicked!');
    // Navigate to the ticket booking page (you can set up a route for booking)
    // Example: this.router.navigate(['/book-ticket', this.movie.title]);
    alert('Redirecting to ticket booking page...');
  }
}
