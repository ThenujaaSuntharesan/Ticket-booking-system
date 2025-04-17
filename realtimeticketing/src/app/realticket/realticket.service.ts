import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class RealticketService {
  private apiUrl = "http://localhost:8080/api/tickets";
  

  constructor(private httpClient: HttpClient){}
  // Add tickets using Vendor
  addTickets(totalTickets: number, releaseRate: number): Observable<any> {
    return this.httpClient.post(`${this.apiUrl}/vendor/add`, null, {
      params: { totalTickets: totalTickets.toString(), releaseRate: releaseRate.toString() }, responseType: 'text'
    });
  }

  // Buy tickets using Customer
  buyTickets(quantity: number, retrievalRate: number): Observable<any> {
    return this.httpClient.post(`${this.apiUrl}/customer/buy`, null, {
      params: { quantity: quantity.toString(), retrievalRate: retrievalRate.toString() }
    });
  }

  // Get the current status of the ticket pool
  getTicketPoolStatus(): Observable<any> {
    return this.httpClient.get(`${this.apiUrl}/status`);
  }

  //startSystem(): Observable<any> {
   // return this.httpClient.post(`${this.apiUrl}/start`, null, { responseType: 'text' });
  //}
  startSystem(totalTickets: number, releaseRate: number, quantity: number, retrievalRate: number): Observable<any> {
    return this.httpClient.post(
      `${this.apiUrl}/start`,
      null,
      {
        params: {
          totalTickets: totalTickets.toString(),
          releaseRate: releaseRate.toString(),
          quantity: quantity.toString(),
          retrievalRate: retrievalRate.toString()
        },
        responseType: 'text'
      }
    );
  }
  // Stop the system
  stopSystem(): Observable<any> {
  return this.httpClient.post(`${this.apiUrl}/stop`, null, { responseType: 'text' });
  }
  
  
}
