import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { AuthService } from '../../services/auth.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
  ],
  // providers: [AuthService],
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css'],
})
export class RegisterPageComponent {
  // Define form fields for data binding
  firstName: string = '';
  lastName: string = '';
  address: string = '';
  phoneNumber: string = '';
  email: string = '';
  password: string = '';
  confirmPassword: string = '';

  constructor(private authService: AuthService) {}

  // Method to handle form submission
  onRegister() {
    // Validation for matching passwords
    if (this.password !== this.confirmPassword) {
      alert('Passwords do not match!');
      return;
    }
  
    // Additional validation for required fields
    if (
      !this.firstName ||
      !this.lastName ||
      !this.email ||
      !this.address ||
      !this.phoneNumber ||
      !this.password ||
      !this.confirmPassword
    ) {
      alert('All fields are required!');
      return;
    }
  
    // Prepare form data
    const userData = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      address: this.address,
      phoneNumber: this.phoneNumber,
      password: this.password,
    };
  
    // Make HTTP POST request
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    this.authService.register(userData, {headers}).subscribe({
      next: (response) => {
        alert('Registration successful!');
        console.log('Registration Response:', response); // Log the response for debugging
      },
      error: (error) => {
        console.error('Error during registration:', error);
        if (error.status === 400) {
          // Check if the error contains a specific message from the backend
          if (error.error && error.error.message) {
            alert('Error: ' + error.error.message); // Show backend message if available
          } else {
            alert('Registration failed. Please try again.');
          }
        } else {
          alert('An unexpected error occurred. Please try again.');
        }
      },
    });
  }  
}
