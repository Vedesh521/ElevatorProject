package com.elevator.Lift;

import java.util.*;

public class Elevator {
    private static final int MIN_FLOOR = 0;
    private static final int MAX_FLOOR = 10;
    private static int processingTime = 500; // in milliseconds

    private int currentFloor;
    private Direction currentDirection;
    private Map<Integer, List<Integer>> requestedPathsMap;
    private Boolean[] currentFloorDestinations;

    public Elevator() {
        this.currentFloor = 0;
        this.currentDirection = Direction.UP;
        this.requestedPathsMap = new HashMap<>();
        this.currentFloorDestinations = new Boolean[MAX_FLOOR + 1];
        Arrays.fill(this.currentFloorDestinations, Boolean.FALSE);
    }

    public void setProcessingTime(int processingTime) {
        Elevator.processingTime = processingTime;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    public Map<Integer, List<Integer>> getRequestedPathsMap() {
        return this.requestedPathsMap;
    }

    public Boolean[] getCurrentFloorDestinations() {
        return this.currentFloorDestinations;
    }

    public void start() throws InterruptedException {
        currentDirection = Direction.UP;

        do {
            System.out.println("--------");
            processFloor(currentFloor);
            System.out.println("--------");
        } while (currentDirection != Direction.IDLE);

        System.out.println("No one is waiting and no one is looking to go anywhere.");
        System.out.println("Elevator is IDLE now.");
    }

    public void lunchtimeElevatorRush() {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            int start = random.nextInt(11);
            int dest;
            do {
                dest = random.nextInt(11);
            } while (dest == start);

            callElevator(start, dest);
        }
    }

    public void callElevator(int start, int destination) {
        if (isInvalidFloor(start) || isInvalidFloor(destination) || start == destination) {
            System.out.println("INVALID FLOORS. Try again");
            return;
        }

        if (requestedPathsMap.containsKey(start)) {
            requestedPathsMap.get(start).add(destination);
        } else {
            List<Integer> destinations = new ArrayList<>();
            destinations.add(destination);
            requestedPathsMap.put(start, destinations);
        }
    }

    private void processFloor(int floor) throws InterruptedException {
        if (currentFloorDestinations[floor]) {
            System.out.println("UN-BOARDING at Floor: " + floor);
        }

        if (requestedPathsMap.containsKey(floor)) {
            System.out.println("BOARDING at Floor: " + floor);
            for (int destinationFloor : requestedPathsMap.get(floor)) {
                currentFloorDestinations[destinationFloor] = true;
            }
            requestedPathsMap.remove(floor);
        }

        currentFloorDestinations[floor] = false;
        moveElevator();
    }

    private void moveElevator() throws InterruptedException {
        if (!Arrays.asList(currentFloorDestinations).contains(true) && requestedPathsMap.isEmpty()) {
            currentDirection = Direction.IDLE;
            return;
        }

        if (isInvalidFloor(currentFloor + 1)) {
            currentDirection = Direction.DOWN;
        } else if (isInvalidFloor(currentFloor - 1)) {
            currentDirection = Direction.UP;
        }

        switch (currentDirection) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            default:
                System.out.println("Elevator Malfunctioned!");
        }
    }

    private void moveUp() throws InterruptedException {
        currentFloor++;
        System.out.println("GOING UP TO Floor: " + currentFloor);
        Thread.sleep(processingTime);
    }

    private void moveDown() throws InterruptedException {
        currentFloor--;
        System.out.println("GOING DOWN TO Floor: " + currentFloor);
        Thread.sleep(processingTime);
    }

    private boolean isInvalidFloor(int floor) {
        return floor < MIN_FLOOR || floor > MAX_FLOOR;
    }
    
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

}
