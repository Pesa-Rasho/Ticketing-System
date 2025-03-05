package com.ticketing.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The InputValidation class provides methods for validating user input.
 * It includes a method to ensure that the input is a positive integer.
 */
public class InputValidation {
    /**
     * Validates and reads a positive integer input from the user.
     *
     * @param sc The Scanner instance to read user input.
     * @param prompt The prompt message to display to the user.
     * @return A positive integer value provided by the user.
     */
    public static int positiveIntegerInput(Scanner sc, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = sc.nextInt();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be greater than 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
                sc.next();
            }
        }
    }
}
