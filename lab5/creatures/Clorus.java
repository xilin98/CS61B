package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of Clorus.
 *
 * @author xilin
 */
public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     *  green color.
     */
    private int g;
    /**
     *  blue color.
     */
    private int b;

    /**
     *  creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Should return a color with red = 34, blue = 231, and green = 0.
     */
    @Override
    public Color color() {
        return color(r, g, b);
    }

    /**
     * Attact a plip p.
     * It will gain the energy of the plip.
     */
    @Override
    public void attack(Creature p) {
        energy += p.energy();
    }

    /**
     * clorus lose 0.03 unit of energy on a MOVE action.
     */
    @Override
    public void move() {
        double losingEnergy = 0.03;
        energy -= losingEnergy;
    }

    /**
     * clorus lose 0.01 unit of energy on STAY action.
     */
    @Override
    public void stay() {
        double losingEnergy = 0.01;
        energy -= losingEnergy;
    }

    @Override
    public Clorus replicate() {
        energy = energy * 0.5;
        return new Clorus(energy);
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> surrounded){
        Deque<Direction> neighbors_empty = new ArrayDeque<>();
        Deque<Direction> neighborsPlip = new ArrayDeque<>();
        for (Direction d : surrounded.keySet()) {
            if (surrounded.get(d).name().equals("empty")) {
                neighbors_empty.add(d);
            }
            if (surrounded.get(d).name().equals("plip")) {
                neighborsPlip.add(d);
            }
        }

        if (neighbors_empty.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (neighborsPlip.isEmpty()) {
            if (energy() > 1) {
                while (true) {
                    for (Direction d : neighbors_empty) {
                        if (Math.random() <= 1.0 / neighbors_empty.size()) {
                            return new Action(Action.ActionType.REPLICATE, d);
                        }
                    }
                }
            } else {
                while (true) {
                    for (Direction d : neighbors_empty) {
                        if (Math.random() <= 1.0 / neighbors_empty.size()) {
                            return new Action(Action.ActionType.MOVE, d);
                        }
                    }
                }
            }
        } else {
            while (true) {
                for (Direction d : neighborsPlip) {
                    if (Math.random() <= 1.0 / neighborsPlip.size()) {
                        return new Action(Action.ActionType.ATTACK, d);
                    }
                }
            }
        }
    }
}
