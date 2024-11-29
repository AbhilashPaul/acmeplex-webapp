import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Theater {
  id: number;
  name: string;
  address: string;
}

@Injectable({
  providedIn: 'root',
})
export class TheaterService {
  private apiUrl = 'http://localhost:8080/api/theatres';

  constructor(private http: HttpClient) {}

  getTheaters(): Observable<Theater[]> {
    return this.http.get<Theater[]>(this.apiUrl);
  }
}
