import static org.junit.Assert.*;
import org.junit.Test;
public class FilkTest {
    @Test
    public void isSameNumberTest(){
        int input1 = 1, input2 = 1;
        boolean actual = Flik.isSameNumber(input1, input2);
        assertTrue(actual);
    }
}
