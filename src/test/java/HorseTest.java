import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;


public class HorseTest {
    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nullNameMessage() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Name cannot be null.", exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "\t", "\n\n"})
    public void blankNameException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "\t", "\n\n"})
    public void blankNameMessage(String name) {
        try {
            new Horse(name, 1, 1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Name cannot be blank.", exception.getMessage());
        }
    }

    @Test
    public void negativeSpeedException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1, 1));
    }

    @Test
    public void negativeSpeedMessage() {
        try {
            new Horse("name", -1, 1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Speed cannot be negative.", exception.getMessage());
        }
    }

    @Test
    public void negativeDistanceException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1, -1));
    }

    @Test
    public void negativeDistanceMessage() {
        try {
            new Horse("name", 1, -1);
        } catch (IllegalArgumentException exception) {
            assertEquals("Distance cannot be negative.", exception.getMessage());
        }
    }

    @Test
    public void getName() {
        assertEquals("name", new Horse("name", 1, 1).getName());
    }

    @Test
    public void getSpeed() {
        assertEquals(1, new Horse("name", 1, 1).getSpeed());
    }

    @Test
    public void getDistance() {
        assertEquals(1, new Horse("name", 1, 1).getDistance());
    }

    @Test
    public void getDistanceDefault() {
        assertEquals(0, new Horse("name", 1).getDistance());
    }

    @Test
    public void getRandomDoubleInMoveMethod() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {

            new Horse("name", 1, 1).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 0.9, 99.99, 0.0})
    public void move(double random) {
        double speed = 5;
        double distance = 76;
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {

            Horse horse = new Horse("name", speed, distance);

            mockedStatic.when(()-> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(distance += speed * random, horse.getDistance());

        }
    }


}
