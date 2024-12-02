import { Routes } from '@angular/router';
import { MovieDetailsComponent } from './movie-details/movie-details.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { PaymentComponent } from './components/payment/payment.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { TheaterDetailsComponent } from './components/theater-details/theater-details.component';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import { CancelTicketComponent } from './components/cancel-ticket/cancel-ticket.component';
import { AnnualFeePaymentComponent } from './components/annual-fee-payment/annual-fee-payment.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'login', component: LoginPageComponent},
  { path: 'profile', component: ProfilePageComponent},
  { path: 'movie-details', component: MovieDetailsComponent },
  { path: 'payment', component: PaymentComponent },
  { path: 'theater-details', component: TheaterDetailsComponent },
  { path: 'movies/:title', component: MovieDetailsComponent },
  { path: 'cancel-ticket', component: CancelTicketComponent },
  { path: 'annual-payment', component: AnnualFeePaymentComponent },
];
