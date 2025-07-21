# Elevator Simulation Project

This Java-based Elevator Simulation models the behavior of a basic elevator system. It demonstrates object-oriented programming principles such as encapsulation, inheritance, abstraction, and unit testing using JUnit.

## 🧾 Project Structure

- `Direction.java` - Enum to define the direction of elevator movement (UP, DOWN, IDLE).
- `Elevator.java` - The core elevator class managing floor requests, current floor, and movement logic.
- `ElevatorChallenge.java` - The main class to simulate the elevator system using command-line inputs.
- `ElevatorTest.java` - JUnit test cases to validate elevator behavior.

## ✅ Features

- Handles multiple floor requests.
- Moves the elevator based on current direction and pending requests.
- Displays elevator status (floor, direction).
- JUnit-based unit testing for verifying elevator logic.

## 🛠️ Technologies Used

- Java 8+
- JUnit 4 (for testing)
- Command Line Interface (CLI) based interaction

## 📁 How to Run

1. **Compile the Java files:**

```bash
javac Direction.java Elevator.java ElevatorChallenge.java ElevatorTest.java
