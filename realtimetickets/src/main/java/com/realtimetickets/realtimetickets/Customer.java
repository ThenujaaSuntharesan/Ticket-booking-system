package com.realtimetickets.realtimetickets;

import java.util.concurrent.atomic.AtomicBoolean;

public class Customer implements Runnable {

    // Shared ticket pool from which the customer buys tickets
    private TicketPool ticketPool;

    // Rate at which the customer attempts to retrieve tickets (in seconds)
    private int customerRetrievalRate;

    // Maximum number of tickets the customer will attempt to purchase
    private int quantity;

    // Flag to control the running state of the customer thread
    private AtomicBoolean running;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
        this.running = new AtomicBoolean(true);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started purchasing tickets.");
        int ticketsPurchased = 0;// Counter to track tickets purchased

        while (running.get() && ticketsPurchased < quantity) {
            // Attempt to buy a ticket from the pool
            Ticket ticket = ticketPool.buyTicket();
            if (ticket != null) {
                System.out.println(Thread.currentThread().getName() + " purchased ticket: " + ticket);
                ticketsPurchased++;
            } else {
                System.out.println(Thread.currentThread().getName() + " couldn't purchase a ticket. Pool might be empty.");
            }

            // Pause for the retrieval rate duration to simulate ticket purchase interval
            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                // Handle thread interruption gracefully
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " was interrupted.");
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished purchasing tickets. Total purchased: " + ticketsPurchased);
    }

    public void stop() {
        running.set(false);
    }
}

