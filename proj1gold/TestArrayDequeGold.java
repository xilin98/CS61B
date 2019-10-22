import org.junit.Test;
import org.junit.Assert.*;

public class TestArrayDequeGold {

    @Test
    public void randomTest(){
        StudentArrayDeque<Integer> s = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> a = new ArrayDequeSolution<>();
        for (Integer i = 0; i < 10; i += 1) {
            double numberOneToTen = StdRandom.uniform(10);
            if (numberOneToTen < 5) {
                s.addFirst(i);
                a.addFirst(i);
            } else {
                s.addLast(i);
                a.addLast(i);
            }
        }
        for (int i = 0; i < 5; i += 1 ) {
            assertEquals(s.removeFirst(), a.removeFirst());
        }
    }
}
