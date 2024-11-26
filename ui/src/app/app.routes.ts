import { Routes } from '@angular/router';
import { MovieDetailsComponent } from './movie-details/movie-details.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { PaymentComponent } from './components/payment/payment.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { TheaterDetailsComponent } from './components/theater-details/theater-details.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'login', component: LoginPageComponent},
  { path: 'movie-details', component: MovieDetailsComponent },
  { path: 'payment', component: PaymentComponent }
  { path: 'theater-details', component: TheaterDetailsComponent }
];
