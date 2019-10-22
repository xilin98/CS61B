package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF full;
    private WeightedQuickUnionUF open;
    private int N; // The length and height of the grid.
    private int opened = N * N ;
    private int fulled = N * N ;
    private int percolate  = N * N + 1;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.open = new WeightedQuickUnionUF(N * N + 1);
        this.full = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            full.union(N * (N - 1) + i, percolate);
        }
    }

    /** Convert (row, col) into indices. **/
    private int toIndex(int row, int col) {
        return row * N + col;
    }

    /** To check if the input (row, col) is legal.**/
    private void validate(int row, int col) {
        if (row < N && row >= 0 && col < N && col >=0) {
            ;
        } else {
            throw new IllegalArgumentException();
        }
    }
    private int[] neighbor(int row, int col) {
        int i = toIndex(row, col);

        if (row == 0 && col == 0) {
            int [] result = new int[2];
            result[0] = toIndex(row + 1, col);
            result[1] = toIndex(row, col + 1);
            return result;
        }
        else if (row == N - 1 && col == 0) {
            int[] result = new int[2];
            result[0] = toIndex(row - 1, col);
            result[1] = toIndex(row, col + 1);
            return result;
        }
        else if (row == N - 1 && col == N -1) {
            int[] result = new int[2];
            result[0] = toIndex(row - 1, col);
            result[1] = toIndex(row, col + 1);
            return result;
        }
        else if (row == 0 && col == N -1) {
            int[] result = new int[2];
            result[0] = toIndex(row + 1, col);
            result[1] = toIndex(row, col - 1);
            return result;
        }
        else if (row == 0){
            int[] result = new int[3];
            result[0] = toIndex(row, col - 1);
            result[1] = toIndex(row + 1, col);
            result[2] = toIndex(row, col + 1);
            return result;
        }
        else if (col == 0) {
            int[] result = new int[3];
            result[0] = toIndex(row - 1, col);
            result[1] = toIndex(row, col + 1);
            result[2] = toIndex(row + 1, col);
            return result;
        }
        else if (row == N - 1) {
            int[] result = new int[3];
            result[0] = toIndex(row - 1, col);
            result[1] = toIndex(row, col - 1);
            result[2] = toIndex(row, col + 1);
            return result;
        }
        else if (col == N -1) {
            int[] result = new int[3];
            result[0] = toIndex(row - 1, col);
            result[1] = toIndex(row, col - 1);
            result[2] = toIndex(row + 1, col);
            return result;
        }
        else {
            int[] result =new int[4];
            result[0] = toIndex(row -1, col);
            result[1] = toIndex(row, col - 1);
            result[2] = toIndex(row + 1, col);
            result[3] = toIndex(row, col + 1);
            return result;
        }
    }
    /** open the site if it not open already. **/
    public void open(int row, int col) {
        validate(row, col);
        int x = toIndex(row, col);
        open.union(x, opened);
        if (row == 0) {
            full.union(x, fulled);
        }
        for (int i : neighbor(row, col)) {
            if (open.connected(i, x)) {
                full.union(i, x);
            }
        }
    }

    /** Is the site (row, col) open? **/
    public boolean isOpen(int row, int col) {
        validate(row, col);
        int i = toIndex(row, col);
        return open.connected(i, opened);
    }

    /** Is the site (row, col) full? **/
    public boolean isFull(int row, int col) {
        validate(row, col);
        return full.connected(toIndex(row, col), fulled);
    }

    /** number of the open site. **/
    public int numberOfOpenSites(){
        return open.find(opened) - 1;
    }

    /** Does the system percolates? **/
    public boolean percolates() {
        return full.connected(percolate, fulled);
    }

    public static void main(String[] args) {}
}
