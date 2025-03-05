package com.ticketing.cli;

import java.util.Scanner;

/**
 * The Main class is the entry point for the ticketing system CLI application.
 * It provides a menu-driven interface for configuring, starting, and stopping the system.
 */
public class Main {

    public static void main(String[] args) {

        AdminSystem adminSystem = new AdminSystem();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        /**
         * The main loop of the application, which displays the menu and handles user input.
         */
        while (!exit) {
            System.out.println("\n--Ticketing System--");
            System.out.println("1. Configure System");
            System.out.println("2. Start Operations");
            System.out.println("3. Stop Operations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            /**
             * Reads a positive integer input from the user, ensuring it is within the valid range.
             */
            int choice = InputValidation.positiveIntegerInput(scanner, "Enter your choice (1-4): ");
            if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                continue;
            }

            /**
             * Handles the user's choice and performs the corresponding action.
             */
            switch (choice) {
                case 1 -> adminSystem.configureSystem(scanner);
                case 2 -> adminSystem.start();
                case 3 -> adminSystem.stop();
                case 4 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        /**
         * Prints an exit message and closes the scanner.
         */
        System.out.println("Exiting...");
        scanner.close();
    }
}