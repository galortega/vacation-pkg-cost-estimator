package com.espol.edu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt user for input
            System.out.print("Enter destination: ");
            String destination = scanner.nextLine();

            System.out.print("Enter number of travelers: ");
            int numTravelers = scanner.nextInt();

            System.out.print("Enter duration of vacation (in days): ");
            int duration = scanner.nextInt();

            // Create new VacationPackage instance and calculate cost
            VacationPackage vacationPackage = new VacationPackage(destination, numTravelers, duration);
            int totalCost = vacationPackage.calculateCost();

            // Print result to console
            if (totalCost == -1) {
                System.out.println("Invalid input data.");
            } else {
                System.out.println("Total cost of vacation package: $" + totalCost);
            }
        }
    }
}