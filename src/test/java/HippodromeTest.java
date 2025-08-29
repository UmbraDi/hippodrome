import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    public void testNullHorsesThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
    }

    @Test
    public void testNullHorsesExceptionMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void testEmptyHorsesThrowsException() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(horses)
        );
    }

    @Test
    public void testEmptyHorsesExceptionMessage() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(horses)
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testHorsesIdentityAfterConstruction() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse №" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testMoveForAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse: horses) {
            verify(horse).move();
        }
    }

    @Test
    public void testGetWinner() {
        Horse apple = new Horse("Apple", 1, 1);
        Horse cherry = new Horse("Cherry", 2, 2);
        Horse mint = new Horse("Mint", 3, 3);
        Hippodrome hippodrome = new Hippodrome(List.of(apple, cherry, mint));
        assertSame(mint, hippodrome.getWinner());
    }
}