package com.shadowfox.helpdesk.util;

import java.util.Scanner;

public class InputValidator {

    // Check whether input is empty
    public static boolean isEmpty(String input) {

        return input == null || input.trim().isEmpty();
    }


    // Check whether priority is valid
    public static boolean isValidPriority(String priority) {

        return priority.equalsIgnoreCase("Low")
                || priority.equalsIgnoreCase("Medium")
                || priority.equalsIgnoreCase("High");
    }


    // Check whether category is valid
    public static boolean isValidCategory(String category) {

        return category.equalsIgnoreCase("Technical")
                || category.equalsIgnoreCase("Academic")
                || category.equalsIgnoreCase("General");
    }


    // Check whether input is a valid integer
    public static boolean isValidInteger(String input) {

        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        try {

            Integer.parseInt(input);

            return true;

        } catch (NumberFormatException e) {

            return false;
        }
    }


    // Read an integer safely
    public static int readInteger(
            Scanner scanner,
            String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine();

            if (isValidInteger(input)) {

                return Integer.parseInt(input);
            }

            System.out.println(
                    "Invalid number. Please enter a valid integer."
            );
        }
    }
}