import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class TheaterService {
  private apiUrl = environment.apiUrl; // Backend URL

  constructor(private http: HttpClient) {}

  // Fetch theaters for a given movie ID
  getTheaters(movieId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}?movieId=${movieId}`);
  }
}
