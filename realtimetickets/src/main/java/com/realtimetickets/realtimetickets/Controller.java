package com.realtimetickets.realtimetickets;

// Import necessary libraries and annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Mark this class as a REST controller and map its base URL to "/api/tickets"
@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    // A shared pool of tickets managed by the system
    private TicketPool ticketPool = new TicketPool(10);

    // Executor service to handle vendor and customer threads
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    // Vendor and Customer objects to simulate ticket production and consumption
    private Vendor vendor;
    private Customer customer;

    // Repository to handle database interactions (currently unused in this code snippet)
    @Autowired
    private RealTimeTicketRepository realTimeTicketRepository;

    @PostMapping("/start")
    public String startSystem(@RequestParam int totalTickets, @RequestParam int releaseRate,
                              @RequestParam int quantity, @RequestParam int retrievalRate) {
        stopSystem(); // Stop any existing threads before starting a new system


        // Initialize the ticket pool with a size sufficient for all tickets (Ensure pool can hold all tickets)
        ticketPool = new TicketPool(Math.max(totalTickets, quantity));

        // Create a new vendor and customer with the specified rates
        vendor = new Vendor(ticketPool, releaseRate, totalTickets);
        customer = new Customer(ticketPool, retrievalRate, quantity);

        // Submit vendor and customer tasks to the executor service for concurrent execution
        executorService.submit(vendor);
        executorService.submit(customer);

        return "System started with Vendor and Customer threads.";
    }

    @PostMapping("/stop")
    public String stopSystem() {
        // Stop the vendor thread if it exists
        if (vendor != null) {
            vendor.stop();
        }
        // Stop the customer thread if it exists
        if (customer != null) {
            customer.stop();
        }
        // Clear all tickets from the pool
        ticketPool.clearPool();
        return "System stopped and ticket pool cleared.";
    }

    @GetMapping("/status")
    public String getTicketPoolStatus() {
        return "Current ticket pool size: " + ticketPool.getTicketQueueSize();
    }
}

