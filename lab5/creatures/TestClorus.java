package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2.0);
        Clorus c_offspring = c.replicate();
        assertEquals(1.00, c.energy(), 0.01);
        assertEquals(1.00, c_offspring.energy(), 0.01);
        assertNotEquals(c, c_offspring);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(2.0);
        Plip p = new Plip(2.0);
        c.attack(p);
        assertEquals(c.energy(), 4.0, 0.01);
    }

    @Test
    public void testChoose() {
        Clorus c = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Plip(1.0));
        surrounded.put(Direction.BOTTOM, new Plip(1.0));
        surrounded.put(Direction.LEFT, new Plip(1.0));
        surrounded.put(Direction.RIGHT, new Plip(1.0));

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        HashMap<Direction, Occupant> topEmpty = new HashMap<>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Plip(1.0));
        topEmpty.put(Direction.LEFT, new Plip(1.0));
        topEmpty.put(Direction.RIGHT, new Plip(1.0));

        actual = c.chooseAction(topEmpty);
        assertNotEquals(actual, new Action(Action.ActionType.STAY));
        assertNotEquals(actual, new Action(Action.ActionType.MOVE, Direction.TOP));
    }
}
