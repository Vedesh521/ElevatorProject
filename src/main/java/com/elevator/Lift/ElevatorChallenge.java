package com.elevator.Lift;

import java.util.Scanner;

public class ElevatorChallenge {

    // Method to simulate random elevator calls
    static void automaticElevator() throws InterruptedException {
        Elevator elevator = new Elevator();
        elevator.lunchtimeElevatorRush(); // 30 random requests
        elevator.start();
    }

    // Method to allow user to manually call the elevator
    static void manualElevator() throws InterruptedException {
        Elevator elevator = new Elevator();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("\nEnter a starting floor (0 - 10): ");
            int start = sc.nextInt();
            System.out.print("Enter a destination floor (0 - 10): ");
            int end = sc.nextInt();

            elevator.callElevator(start, end); // user input
            System.out.print("Do you want to add more requests? (y/n): ");
            String more = sc.next();
            if (!more.equalsIgnoreCase("y")) break;
        }

        elevator.start(); // Start processing requests
        sc.close();
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Elevator Simulation");
        System.out.println("1. Manual Mode");
        System.out.println("2. Automatic Random Mode");
        System.out.print("Choose mode (1 or 2): ");
        int choice = sc.nextInt();

        if (choice == 1) {
            manualElevator();
        } else {
            automaticElevator();
        }

        sc.close();
    }
}
