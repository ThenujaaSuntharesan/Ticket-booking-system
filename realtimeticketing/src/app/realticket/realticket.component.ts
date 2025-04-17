import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RealticketService } from './realticket.service';
import { Realticket } from './realticket.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-realticket',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './realticket.component.html',
  styleUrls: ['./realticket.component.css']
})
export class RealticketComponent {
  constructor(private realticketService: RealticketService){}
  newTicket: Realticket = {
    eventName: "", ticketPrice: 0,
    quantity: 0,
    retrievalRate: 0,
    releaseRate: 0,
    totalTickets: 0
  };
  processLogs: string[] = [];
  quantity: number = 0;
  retrievalRate: number = 0;
  releaseRate: number = 0;
  totalTickets: number = 0;

  // Add tickets using Vendor
  addTickets() {
    this.realticketService.addTickets(this.totalTickets, this.releaseRate).subscribe(
      (response) => {
        console.log("Vendor started adding tickets", response);
        this.processLogs.push(response);
      },
      (error) => {
        console.error("Error adding tickets:", error);
        this.processLogs.push("Error adding tickets.");
      }
    );
  }

  // Buy tickets using Customer
  buyTickets() {
    this.realticketService.buyTickets(this.quantity, this.retrievalRate).subscribe(
      (response) => {
        console.log("Customer started purchasing tickets", response);
        this.processLogs.push("Customer started purchasing tickets.");
      },
      (error) => {
        console.error("Error purchasing tickets:", error);
        this.processLogs.push("Error purchasing tickets.");
      }
    );
  }

  // Get ticket pool status
  checkStatus() {
    this.realticketService.getTicketPoolStatus().subscribe(
      (status) => {
        console.log("Ticket pool status:", status);
        this.processLogs.push(`Ticket pool status: ${status}`);
      },
      (error) => {
        console.error("Error fetching status:", error);
        this.processLogs.push("Error fetching status.");
      }
    );
  }
  startSystem() {
    this.realticketService.startSystem(
      this.totalTickets,
      this.releaseRate,
      this.quantity,
      this.retrievalRate
    ).subscribe(
      (response) => {
        console.log("System started successfully:", response);
        this.processLogs.push("System started successfully.");
      },
      (error) => {
        console.error("Error starting the system:", error);
        this.processLogs.push("Error starting the system.");
      }
    );
  }
  
  stopSystem() {
    this.realticketService.stopSystem().subscribe(
      (response) => {
        console.log("System stopped successfully:", response);
        this.processLogs.push("System stopped successfully.");
      },
      (error) => {
        console.error("Error stopping the system:", error);
        this.processLogs.push("Error stopping the system.");
      }
    );
  }

}
