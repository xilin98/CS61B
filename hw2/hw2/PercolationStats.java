package hw2;

public class PercolationStats {
    private int[] result;
    private int T;
    /** perform T independent experiments on an N-by-N grid. **/
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        result =new int[T];
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int x = edu.princeton.cs.introcs.StdRandom.uniform(N);
                int y = edu.princeton.cs.introcs.StdRandom.uniform(N);
                p.open(x, y);
            }
            result[i] = p.numberOfOpenSites();
        }
    }
    /** Sample mean of percolation threshold. **/
    public double mean() {
        int sum = 0;
        for (int i = 0; i < T; i++) {
            sum += result[i];
        }
        return (double)sum/T;
    }

    /** Sample standard deviation of percolation threshold. **/
    public double stddev() {
        return edu.princeton.cs.introcs.StdStats.stddev(result);
    }

    /** Low endpoint of 95% confidence interval. **/
    public double confidenceLow() {
        return mean() - Math.sqrt(stddev()/T) * 1.96;
    }

    /** Height endpoint of 95% confidence interval **/
    public double confidenceHigh() {
        return mean() + Math.sqrt(stddev()/T) * 1.96;
    }
}
