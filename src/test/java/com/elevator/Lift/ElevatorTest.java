package com.elevator.Lift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class ElevatorTest {

    private Elevator elevator;

    @BeforeEach
    public void setUp() {
        elevator = new Elevator();
    }

    @Test
    public void testInitialFloorIsZero() {
        assertEquals(0, elevator.getCurrentFloor(), "Elevator should start at floor 0");
    }

    @Test
    public void testValidCallElevator() {
        elevator.callElevator(2, 5);
        Map<Integer, List<Integer>> map = elevator.getRequestedPathsMap();
        assertTrue(map.containsKey(2));
        assertEquals(1, map.get(2).size());
        assertEquals(5, map.get(2).get(0));
    }

    @Test
    public void testInvalidCallSameFloor() {
        elevator.callElevator(4, 4);
        assertTrue(elevator.getRequestedPathsMap().isEmpty(), "Same floor call should be ignored");
    }

    @Test
    public void testInvalidCallOutOfRange() {
        elevator.callElevator(-1, 15);
        assertTrue(elevator.getRequestedPathsMap().isEmpty(), "Out of range floors should be ignored");
    }

    @Test
    public void testMultipleCallsToSameStartFloor() {
        elevator.callElevator(1, 3);
        elevator.callElevator(1, 5);
        assertEquals(2, elevator.getRequestedPathsMap().get(1).size());
    }

    @Test
    public void testIdleAfterStart() throws InterruptedException {
        elevator.callElevator(0, 2);
        elevator.start();
        assertSame(Direction.IDLE, elevator.getCurrentDirection()); // ✅ Use this for enums
        assertEquals(2, elevator.getCurrentFloor());
    }
}
