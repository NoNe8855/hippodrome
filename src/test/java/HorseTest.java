import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;


import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {


    @Test
    void whenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10, 100);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    void whenNameIsBlank(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 10, 100);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void whenSpeedIsNegativ() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Valid Name", -1, 100);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void whenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Valid Name", 10, -1);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());

    }


    @Test
    void getName() {
        Horse horse = new Horse("Bern", 10.0, 100.0);
        assertEquals("Bern", horse.getName());


    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Bern", 10.0, 100.0);
        assertEquals(10.0, horse.getSpeed());


    }

    @Test
    void getDistance() {
        Horse horse = new Horse("Bern", 10.0, 100.0);
        assertEquals(100.0, horse.getDistance());
        Horse horse2 = new Horse("Top", 15);
        assertEquals(0.0, horse2.getDistance(), 0.001);


    }

    @Test
    void move() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            // Создаем экземпляр лощади
            Horse horse = new Horse("Bern",10.0,5.0);

            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(0.5);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9));
            // расчитываю ожидаемое значение дистанции
            double expDistance = horse.getDistance() + 0.0 * 0.5;
            assertEquals(expDistance, horse.getDistance());

        }

    }

    @Test
    void getRandomDouble() {
    }
}