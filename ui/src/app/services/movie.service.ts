import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Movie {
  id: number;
  title: string;
  description: string;
  duration: string;
  genre: string;
  movieRating: string;
  imageUrl: string;
}

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  private apiUrl = 'http://localhost:8080/api/movies'; // Backend API URL

  constructor(private http: HttpClient) {}

  // Fetch movies from the backend
  getMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.apiUrl);
  }
}
