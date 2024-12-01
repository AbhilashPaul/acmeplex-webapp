import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SessionStoreService {

  constructor() { }
  setUser(data: any) {
    sessionStorage.setItem('user', JSON.stringify(data));
  }

  getUser() {
    const userData = sessionStorage.getItem('user');
    return userData ? JSON.parse(userData) : null;
  }

  clearUser() {
    sessionStorage.removeItem('user');
  }
}
