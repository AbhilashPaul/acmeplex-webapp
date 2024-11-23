import { Routes } from '@angular/router';
import { MovieDetailsComponent } from './components/movie-details/movie-details.component';
import { HomePageComponent } from './components/home-page/home-page.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'movie-details', component: MovieDetailsComponent },
];
