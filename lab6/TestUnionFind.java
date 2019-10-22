import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A basic test for UnionFind
 */
public class TestUnionFind {
    @Test
    public void testBase() {
        UnionFind S = new UnionFind(5);
        S.union(1,2);
        S.union(3,4);
        assertFalse(S.connected(1,4));
        assertEquals(S.sizeOf(1),2);
        S.union(1,4);
        assertTrue(S.connected(2,3));
    }
}
