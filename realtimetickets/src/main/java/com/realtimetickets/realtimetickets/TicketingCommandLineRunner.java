package com.realtimetickets.realtimetickets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// This class implements the CommandLineRunner interface, providing a command-line interface
// for managing the ticketing system. It handles system initialization, user commands,
// and thread-based execution of vendor and customer operations.

@Component
public class TicketingCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RealTimeTicketRepository realTimeTicketRepository;

    private TicketPool ticketPool;
    private Thread vendorThread;
    private Thread customerThread;
    private boolean systemRunning = false;

    // Path to the JSON file for saving input data
    private static final String JSON_FILE = "ticketing_input_data.json";

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int maxTicketCapacity = 0, totalTickets = 0, ticketReleaseRate = 0, customerRetrievalRate = 0;

        System.out.println("Welcome to the Ticketing System!");

        // Continuously accept commands from the user until EXIT is entered
        while (true) {
            System.out.println("Commands: START | STOP | EXIT");
            String command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case "START":
                    // Prevent starting the system if it's already running
                    if (systemRunning) {
                        System.out.println("System is already running.");
                        break;
                    }

                    // Gather user inputs for system parameters
                    maxTicketCapacity = getInput(scanner, "Enter maximum ticket capacity (e.g., 10): ", 1, Integer.MAX_VALUE);
                    totalTickets = getInput(scanner, "Enter total tickets (e.g., 20): ", 1, maxTicketCapacity);
                    ticketReleaseRate = getInput(scanner, "Enter ticket release rate (in seconds, e.g., 2): ", 1, Integer.MAX_VALUE);
                    customerRetrievalRate = getInput(scanner, "Enter customer retrieval rate (in seconds, e.g., 1): ", 1, Integer.MAX_VALUE);

                    // Save user inputs to a JSON file for persistence
                    saveInputToJson(maxTicketCapacity, totalTickets, ticketReleaseRate, customerRetrievalRate);

                    // Start the ticketing system with the provided parameters
                    startTicketingSystem(maxTicketCapacity, totalTickets, ticketReleaseRate, customerRetrievalRate);
                    break;

                case "STOP":
                    // Prevent stopping the system if it's not running
                    if (!systemRunning) {
                        System.out.println("System is not running.");
                        break;
                    }
                    stopTicketingSystem();
                    break;

                case "EXIT":
                    // Stop the system if running and exit the program
                    if (systemRunning) {
                        stopTicketingSystem();
                    }
                    System.out.println("Exiting the Ticketing System. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid command. Please enter START, STOP, or EXIT.");
            }
        }
    }

    private int getInput(Scanner scanner, String prompt, int minValue, int maxValue) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= minValue && value <= maxValue) {
                    return value;
                }
                System.out.println("Please enter a value between " + minValue + " and " + maxValue + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
    private void saveInputToJson(int maxTicketCapacity, int totalTickets, int ticketReleaseRate, int customerRetrievalRate) {
        ObjectMapper mapper = new ObjectMapper();
        TicketingInputData inputData = new TicketingInputData(maxTicketCapacity, totalTickets, ticketReleaseRate, customerRetrievalRate);

        try {
            mapper.writeValue(new File(JSON_FILE), inputData);
            System.out.println("Input data saved to " + JSON_FILE);
        } catch (IOException e) {
            System.err.println("Failed to save input data to JSON file: " + e.getMessage());
        }
    }

    public void startTicketingSystem(int maxTicketCapacity, int totalTickets, int ticketReleaseRate, int customerRetrievalRate) throws InterruptedException {
        // Initialize ticket pool
        ticketPool = new TicketPool(maxTicketCapacity);

        // Create Vendor and Customer threads
        Vendor vendor = new Vendor(ticketPool, ticketReleaseRate, totalTickets);
        Customer customer = new Customer(ticketPool, customerRetrievalRate, totalTickets);

        // Start threads
        vendorThread = new Thread(vendor, "Vendor");
        customerThread = new Thread(customer, "Customer");
        
        vendorThread.start();
        customerThread.start();

        systemRunning = true;
        System.out.println("Ticketing System started.");
    }

    public void stopTicketingSystem() throws InterruptedException {
        System.out.println("Stopping the Ticketing System...");
        if (vendorThread != null && vendorThread.isAlive()) {
            vendorThread.interrupt();
            vendorThread.join();
        }
        if (customerThread != null && customerThread.isAlive()) {
            customerThread.interrupt();
            customerThread.join();
        }
        systemRunning = false;
        System.out.println("Ticketing System stopped.");
    }
}