import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return  Stream.of(
            Arguments.of(new int[]{4, 2, 3}, new int[]{2, 3, 4}),
            Arguments.of(new int[]{-3, -1, -2, 5}, new int[]{-3, -2, -1, 5}),
            Arguments.of(new int[]{3, -2, -5, -1, 2}, new int[]{-5, -2, -1, 2, 3}),
            Arguments.of(new int[]{-3, 1, 11, 0, 3, 9}, new int[]{-3, 0, 1, 3, 9, 11}),
            Arguments.of(new int[]{7, 3, 2, -2, -1}, new int[]{-2, -1, 2, 3, 7})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0}, {1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
        Integer s;
        int[] res = new int[random_array.length];

        for (int i = 0; i < random_array.length; i++) {
            test.offer(random_array[i]);
        }
        for (int i = 0; i < res.length; i++) {
            res[i] = test.poll();

        }
        assertArrayEquals(res, correct_array);
    }

    @Test
    public void whenExceptionThrown_thenInitialCapacityNotGreaterThanOne() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(0);
        });
    }

    @Test
    public void whenExceptionThrown_thenOfferEisNull() {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        Exception exception = assertThrows(NullPointerException.class, () -> {
            test.offer(null);
        });
    }

    @Test
    public void whenExceptionThrown_thenNoElementCanRemove() {
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            test.remove();
        });
    }
}
