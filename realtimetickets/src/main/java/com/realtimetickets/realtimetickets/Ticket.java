package com.realtimetickets.realtimetickets;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal; //The Ticket class represents a ticket entity in the ticketing system.
  //This class is mapped to a database table using JPA annotations and contains attributes
  //for ticket ID, event name, and ticket price

@Entity
public class Ticket {
    //The unique identifier for the ticket.
     //Marked as the primary key with auto-generated values using IDENTITY strategy.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketID;
    private String eventName;
    private BigDecimal ticketPrice;

    public Ticket() {  //Parameterized constructor for creating a ticket with specified values.

    }


    public String getEventName() {
        return eventName;
    }


    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public Ticket(int ticketID, String eventName, BigDecimal ticketPrice) {
        this.ticketID = ticketID;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", eventName='" + eventName + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}

