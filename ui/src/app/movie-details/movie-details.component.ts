import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-movie-details',
  standalone: true,
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css'],
})
export class MovieDetailsComponent implements OnInit {
  movie: any;

  constructor(private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    this.movie = navigation?.extras.state?.['movie'];
  }

  ngOnInit(): void {
    if (!this.movie) {
      console.error('No movie data passed.');
    }
  }

  getTicket() {
    this.router.navigate(['/theater-details'], {state:{selectedMovie : this.movie}})
  }
}
