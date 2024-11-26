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
    title: 'Harry Potter',
    releaseYear: 2001,
    genre: 'Fantasy, Adventure, Family',
    rating: 8.7,
    description: 'Harry Potter, a young orphan raised by his cruel aunt and uncle, learns on his 11th birthday that he is a wizard. He is invited to attend Hogwarts School of Witchcraft and Wizardry, where he makes new friends and uncovers secrets about his mysterious past. Harry soon learns that he is famous in the wizarding world for surviving a deadly curse cast by the dark wizard, Lord Voldemort. As Harry embarks on his first year at Hogwarts, he faces challenges and mysteries, ultimately confronting the truth about his past and the threat that still looms over the wizarding world.',
    posterUrl: 'assets/HarryPotter.jpg',
  
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
