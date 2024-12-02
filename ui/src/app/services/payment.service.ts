import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  private paymentUrl = 'http://localhost:8080/api/payments/ticket';
  private ticketUrl = 'http://localhost:8080/api/tickets';

  constructor(private http: HttpClient) {}

  makePayment(paymentData: any): Observable<any> {
    return this.http.post(this.paymentUrl, paymentData);
  }

  getTicketDetails(ticketPayload: any): Observable<any> {
    return this.http.post(this.ticketUrl, ticketPayload);
  }
}
