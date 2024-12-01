import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { SessionStoreService } from '../../services/sessionstore.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule,MatIconModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(
    private authService: AuthService,
    private sessionStoreService: SessionStoreService,
  ) {}

  isLoggedIn(): boolean {
    return this.sessionStoreService.getUser() != null
  }
}