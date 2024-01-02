import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void nullNameMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException exception) {
            assertEquals("Horses cannot be null.", exception.getMessage());
        }
    }

    @Test
    public void nullListConstructorException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<Horse>()));
    }

    @Test
    public void nullListConstructorMessage() {
        try {
            new Hippodrome(new ArrayList<Horse>());
        } catch (IllegalArgumentException exception) {
            assertEquals("Horses cannot be empty.", exception.getMessage());
        }
    }

    @Test
    public void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30 ; i++) {
            horses.add(new Horse("Horse "+i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50 ; i++) {
            horses.add(mock(Horse.class));
        }

        horses.forEach(e -> e.move());

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }
    @Test
    public void getWinner(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i <= 5 ; i++) {
            horses.add(new Horse("Horse "+i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        assertEquals(horses.get(5), hippodrome.getWinner());
    }

}
