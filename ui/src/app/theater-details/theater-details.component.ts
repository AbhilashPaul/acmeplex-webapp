// import { Film, Clock, MapPin, Star, Calendar, Ticket } from 'lucide-react';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { NgModule } from '@angular/core';

interface Movie {
  id: number;
  title: string;
  poster: string;
  showtimes: string[];
  genre: string;
  duration: string;
}

interface TheaterDetails {
  name: string;
  address: string;
  rating: number;
  screens: number;
  parkingAvailable: boolean;
}

@Component({
  selector: 'app-theater-details',
  standalone: true,
  imports: [
    CommonModule, 
    FormsModule,
    MatCardModule,
  ],
  templateUrl: './theater-details.component.html',
  styleUrl: './theater-details.component.css'
})
export class TheaterDetailsComponent {
  selectedMovie: Movie | null = null;
  activeTab: 'movies' | 'info' | 'facilities' = 'movies';

  theaterDetails: TheaterDetails = {
    name: "Cineplex Grand Theater",
    address: "123 Cinema Street, Downtown",
    rating: 4.5,
    screens: 12,
    parkingAvailable: true
  }
  movies: Movie[] = [
    { 
      id: 1, 
      title: "Blockbuster Action", 
      poster: "assets/action-poster.jpg", 
      showtimes: ["10:00 AM", "1:30 PM", "4:45 PM", "8:00 PM"],
      genre: "Action",
      duration: "2h 15m"
    },
    { 
      id: 2, 
      title: "Comedy Night", 
      poster: "assets/comedy-poster.jpg", 
      showtimes: ["11:15 AM", "2:45 PM", "5:30 PM", "9:00 PM"],
      genre: "Comedy", 
      duration: "1h 45m"
    }
  ];
  constructor() { }

  ngOnInit(): void {
    // Any initialization logic
  }

  setActiveTab(tab: 'movies' | 'info' | 'facilities'): void {
    this.activeTab = tab;
  }

  selectMovie(movie: Movie): void {
    this.selectedMovie = movie;
  }
}

