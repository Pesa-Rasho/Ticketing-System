package com.ticketing.cli;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The AdminSystem class manages the configuration, start, and stop operations of the ticketing system.
 * It provides methods to configure the system, start and stop operations, display the current configuration,
 * and save the configuration to a file.
 */
public class AdminSystem {
    private final AdminApiClient adminApiClient = new AdminApiClient();
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    private boolean isConfigured = false;
    private boolean isStarted = false;

    /**
     * Configures the system by setting the ticket release rate, customer retrieval rate, and maximum ticket capacity.
     *
     * @param scanner The Scanner instance to read user input.
     */
    public void configureSystem(Scanner scanner) {
        System.out.println("System Configurations");

        ticketReleaseRate = InputValidation.positiveIntegerInput(scanner, "Enter Ticket Release Rate: ");
        customerRetrievalRate = InputValidation.positiveIntegerInput(scanner, "Enter Customer Retrieval Rate: ");
        maxTicketCapacity = InputValidation.positiveIntegerInput(scanner, "Enter Max Ticket Capacity: ");


        isConfigured = true;
        System.out.println("System Configuration Successful");
        displaySystemConfiguration();

        saveConfigurationToFile();
    }

    /**
     * Starts the system if it is configured and not already started.
     */
    public void start() {
        if (!isConfigured) {
            System.out.println("System is not configured. Cannot start.");
            return;
        }

        if (isStarted) {
            System.out.println("System is already started.");
            return;
        }

        boolean isStartedSuccessfully = adminApiClient.startSystem();
        if (isStartedSuccessfully) {
            isStarted = true;
            System.out.println("System Started Successfully.");
        } else {
            System.out.println("Failed to Start System.");
        }
    }

    /**
     * Stops the system if it is started.
     */
    public void stop() {
        if (!isStarted) {
            System.out.println("System is not started. Nothing to stop.");
            return;
        }

        boolean isStoppedSuccessfully = adminApiClient.stopSystem();
        if (isStoppedSuccessfully) {
            isStarted = false;
            System.out.println("System Stopped Successfully.");
        } else {
            System.out.println("Failed to Stop System.");
        }
    }

    /**
     * Displays the current system configuration.
     */
    public void displaySystemConfiguration() {
        System.out.println("\nCurrent System Configuration");
        System.out.println("\tTicket Release Rate: " + ticketReleaseRate);
        System.out.println("\tCustomer Retrieval Rate: " + customerRetrievalRate);
        System.out.println("\tMax Ticket Capacity: " + maxTicketCapacity);
    }

    /**
     * Saves the current system configuration to a file.
     */
    private void saveConfigurationToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("system_config.txt"))) {
            writer.write("Ticket Release Rate: " + ticketReleaseRate + "\n");
            writer.write("Customer Retrieval Rate: " + customerRetrievalRate + "\n");
            writer.write("Max Ticket Capacity: " + maxTicketCapacity + "\n");
            System.out.println("System Configuration saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving configuration to file: " + e.getMessage());
        }
    }
}
