import { Routes } from '@angular/router';
import { MovieDetailsComponent } from './movie-details/movie-details.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { PaymentComponent } from './components/payment/payment.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'movie-details', component: MovieDetailsComponent },
  { path: 'payment', component: PaymentComponent }  // Add this route
];
