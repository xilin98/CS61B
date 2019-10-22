import java.util.LinkedList;

public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int count = 0;
        int newCount = 0;
        int[] result = new int[darts.length];

        count = takeTurn(grid);

        /**Throw the darts.*/
        for (int i = 0; i < darts.length; i += 1) {
            grid[darts[i][0]][darts[i][1]] = 0;
            newCount = takeTurn(grid);
            result[i] = (count > newCount ? count - newCount - 1 : 0);
            count = newCount;
        }
        return result;
    }

    /**
     * Change the grid according to the rule and compute the number of stuck bubbles in the grid.
      */
    private int takeTurn(int[][] grid) {
        int len = grid[0].length;
        int wide = grid.length;
        int total = len * wide;
        UnionFind group =new UnionFind(total + 1);
        for (int row = 0; row < wide; row += 1) {
            for (int col = 0; col < len; col += 1) {
                if (grid[row][col] == 1) {
                    if (row == 0) {
                        group.union(row * len + col, total);
                    }
                    else {
                        if (grid[row - 1][col] == 1) {
                            group.union(row * len + col, (row -1) * len + col);
                        }
                        if (row + 1 != wide && grid[row + 1][col] == 1) {
                            group.union(row * len + col, (row + 1) * len + col);
                        }
                        if (col != 0 && grid[row][col - 1] == 1) {
                            group.union(row * len + col, row * len + (col - 1));
                        }
                        if (col + 1 != len && grid[row][col + 1] == 1) {
                            group.union(row * len + col, row * len + (col + 1));
                        }
                    }
                }
            }
        }
        return group.sizeOf(total);
    }
}
