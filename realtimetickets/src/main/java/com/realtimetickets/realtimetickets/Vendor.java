package com.realtimetickets.realtimetickets;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

public class Vendor implements Runnable {

    // Total number of tickets to be added by the vendor
    private int totalTickets;

    // Rate at which tickets are released (in seconds)
    private int ticketReleaseRate;

    // Shared ticket pool to which tickets are added
    private TicketPool ticketPool;

    // Flag to control the running state of the vendor thread
    private AtomicBoolean running;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int totalTickets) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.totalTickets = totalTickets;
        this.running = new AtomicBoolean(true);// Initialize the running flag to true
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started adding tickets.");
        int ticketsAdded = 0;// Counter to track tickets added

        while (running.get() && ticketsAdded < totalTickets) {
            // Create a new ticket with a unique ID, event name, and price
            Ticket ticket = new Ticket(ticketsAdded, "Event " + ticketsAdded, new BigDecimal("100"));

            // Add the ticket to the shared ticket pool
            ticketPool.addTicket(ticket);
            System.out.println(Thread.currentThread().getName() + " added ticket: " + ticket);
            ticketsAdded++;


            // Pause for the release rate duration to simulate ticket addition interval
            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                // Handle thread interruption gracefully
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished adding tickets. Total added: " + ticketsAdded);
    }

    public void stop() {
        running.set(false);
    }
}

