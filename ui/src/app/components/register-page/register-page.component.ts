import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { HttpHeaders } from '@angular/common/http';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

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
    RouterModule,
  ],
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

  constructor(private router: Router) {}

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

    // Prepare user data to pass to the annual payment page
    const userData = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      address: this.address,
      phoneNumber: this.phoneNumber,
      password: this.password,
    };

    // Redirect to the annual-payment component with user data
    this.router.navigate(['/annual-payment'], { state: { userData } });
  }
}
