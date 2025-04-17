package com.realtimetickets.realtimetickets;



// Class to encapsulate input data for the ticketing system
public class TicketingInputData {

    // Maximum capacity of tickets that can be held in the pool
    private int maxTicketCapacity;

    // Total number of tickets to be released by the vendor
    private int totalTickets;

    // Rate at which the vendor releases tickets (e.g., tickets per second)
    private int ticketReleaseRate;

    // Rate at which the customer retrieves tickets from the pool (e.g., tickets per second)
    private int customerRetrievalRate;

    public TicketingInputData(int maxTicketCapacity, int totalTickets, int ticketReleaseRate, int customerRetrievalRate) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
    }


    public TicketingInputData() {

    }

    // Getters and Setters
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }
}
