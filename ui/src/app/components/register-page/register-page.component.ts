import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule
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
  password: string = '';
  confirmPassword: string = '';

  // Method to handle form submission
  onRegister() {
    if (this.password !== this.confirmPassword) {
      alert('Passwords do not match!');
      return;
    }

    const formData = {
      firstName: this.firstName,
      lastName: this.lastName,
      address: this.address,
      phoneNumber: this.phoneNumber,
      password: this.password,
    };

    console.log('User Registration Data:', formData);
    alert('Registration Successful!');
  }
}
