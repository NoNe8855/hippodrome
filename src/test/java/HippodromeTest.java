import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    void whenIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    void whenIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(Collections.emptyList());
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }


    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horses", 10.0, 100.0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnHorses = hippodrome.getHorses();

        assertEquals(horses.size(), returnHorses.size());
    }

    @Test
    void move() {

        // создаем 50 моков лощадей
        List<Horse> horses = new ArrayList<>();
        for (int i =0; i <= 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        // проверяем что метод mov был вызван для каждой лощади
        for (Horse horse : horses) {
            verify(horse, times(1)).move();
        }

    }

    @Test
    void getWinner() {

        // создаем моки лощадей с разными дистациями
        Horse horse1 = mock(Horse.class);
        Horse horse2 = mock(Horse.class);
        Horse horse3 = mock(Horse.class);

        // устанавливаем растояние
        when(horse1.getDistance()).thenReturn(10.0);
        when(horse2.getDistance()).thenReturn(15.0);
        when(horse3.getDistance()).thenReturn(12.0);

        // созадем иподром слощадьми
        List<Horse> horses = Arrays.asList(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);

        // получаем победителя
        Horse winner = hippodrome.getWinner();
        assertEquals(horse2,winner);

    }
}