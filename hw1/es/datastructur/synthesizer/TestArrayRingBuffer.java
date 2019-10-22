package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
        BoundedQueue<Integer> input = new ArrayRingBuffer<>(3);
        assertTrue(input.isEmpty());
        assertEquals(input.capacity(), 3);
        input.enqueue(1);
        input.enqueue(2);
        input.enqueue(3);
        assertTrue(input.isFull());
        Integer a = input.dequeue();
        Integer x = 1;
        assertEquals(a, x);
    }
}
