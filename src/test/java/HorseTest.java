import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {


    @Test
    public void testNullNameThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 20)
        );
    }

    @Test
    public void testNullNameExceptionMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 20)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\r", "\f"})
    public void testBlankNameThrowsException(String argument) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(argument, 20)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n", "\r", "\f"})
    public void testBlankNameExceptionMessage(String argument) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(argument, 20)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testNegativeValueAsSpeedThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Apple", -5)
        );
    }

    @Test
    public void testNegativeValueAsSpeedExceptionMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Apple", -5)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testNegativeValueAsDistanceThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Apple", 10, -10)
        );
    }

    @Test
    public void testNegativeValueAsDistancerExceptionMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Apple", 10, -10)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetNameReturnsInitialValue() {
        Horse horse = new Horse("Apple", 10);
        assertEquals("Apple", horse.getName());
    }

    @Test
    public void testGetSpeedReturnsInitialValue() {
        Horse horse = new Horse("Apple", 10);
        assertEquals(10, horse.getSpeed());
    }

    @Test
    public void testDefaultDistanceIsZero() {
        Horse horse = new Horse("Apple", 10);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void testGetDistanceReturnsInitialValue() {
        Horse horse = new Horse("Apple", 10, 5);
        assertEquals(5, horse.getDistance());
    }

    @Test
    public void testGetRandomDoubleInvokedWithProperArguments() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
        Horse horse = new Horse("Apple", 10);
        horse.move();
        horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void testDistanceCalculationFormula() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            double randomValue = 0.5;
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(randomValue);
            Horse horse = new Horse("Apple", 10, 5);
            horse.move();
            double newDistance = 5 + 10 * randomValue;
            assertEquals(newDistance, horse.getDistance());
        }
    }



}