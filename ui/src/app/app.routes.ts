import { Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { RegisterPageComponent } from './components/register-page/register-page.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'register', component: RegisterPageComponent },
];
