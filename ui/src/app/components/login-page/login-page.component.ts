import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    RouterModule,
    FormsModule
  ],
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  email: string = '';
  password: string = '';

  constructor(
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router
  ) {}

  onLogin() {
    if (!this.email || !this.password) {
      this.showSnackbar('Please fill in all fields.', 'error');
      return;
    }

    const userData = {
      email: this.email,
      password: this.password,
    };

    this.authService.login(userData).subscribe({
      next: (response) => {
        this.showSnackbar('Login successful!', 'success');
        console.log('Login Response:', response); // Log response for debugging
        // Save token or user details (if applicable)
        this.router.navigate(['/profile']); // Navigate to the profile page
      },
      error: (error) => {
        console.error('Login Error:', error);
        if (error.status === 401) {
          this.showSnackbar('Invalid email or password.', 'error');
        } else {
          this.showSnackbar('An unexpected error occurred.', 'error');
        }
      },
    });
  }

  private showSnackbar(message: string, type: 'success' | 'error') {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      panelClass: type === 'success' ? 'snackbar-success' : 'snackbar-error',
    });
  }
}
