package com.realtimetickets.realtimetickets;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    // Maximum capacity of the ticket pool
    private int maximumTicketCapacity;

    // Queue to store tickets in the pool
    private Queue<Ticket> ticketQueue;

    public TicketPool(int maximumTicketCapacity) {
        this.maximumTicketCapacity = maximumTicketCapacity;
        this.ticketQueue = new LinkedList<>();
    }

    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumTicketCapacity) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting: Queue full.");
                wait();// Wait until there is space in the queue
            } catch (InterruptedException e) {
                e.printStackTrace();// Handle thread interruption
            }
        }
        ticketQueue.add(ticket); // Add the ticket to the queue
        System.out.println(Thread.currentThread().getName() + " added ticket: " + ticket);
        notifyAll(); // Notify other threads (e.g., customers) that a ticket has been added
    }

    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + " waiting: Queue empty.");
                wait(); // Wait until a ticket is available
            } catch (InterruptedException e) {
                e.printStackTrace();// Handle thread interruption
            }
        }
        Ticket ticket = ticketQueue.remove();// Remove the ticket from the queue
        notifyAll(); // Notify other threads (e.g., vendors) that space is available
        return ticket;
    }

    public synchronized int getTicketQueueSize() {
        return ticketQueue.size();
    }
    public synchronized void clearPool() {
        ticketQueue.clear();// Clear all tickets from the pool
        notifyAll();// Notify all waiting threads to stop waiting
    }
}

