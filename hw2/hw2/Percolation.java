package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int numberOfOpenSites;
    private boolean[][] grid;
    private WeightedQuickUnionUF UF;
    private WeightedQuickUnionUF UFwithoutBackWash;

    private int xytonum(int mx, int my) {
        return mx * N + my;
    }

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must > 0");
        }
        this.N = N;
        this.numberOfOpenSites = 0;
        UF = new WeightedQuickUnionUF(N * N + 2);
        UFwithoutBackWash = new WeightedQuickUnionUF(N * N + 2);
        this.grid = new boolean[N][N];
    } // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        int next[][] = new int[][] {
                { 0, 1 },
                { 1, 0 },
                { 0, -1 },
                { -1, 0 }

        };
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException("Out of the bound");
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        numberOfOpenSites++;
        for (int i = 0; i <= 3; i++) {
            int mx = row + next[i][0];
            int my = col + next[i][1];
            if (my < 0 || my >= N) {
                continue;
            }
            if (mx == -1) {
                UFwithoutBackWash.union(xytonum(row, col), N * N);
                UF.union(xytonum(row, col), N * N);
                continue;
            } else if (mx == N) {
                UF.union(xytonum(row, col), N * N + 1);
                continue;
            }
            if (isOpen(mx, my) && !UFwithoutBackWash.connected(xytonum(mx, my), xytonum(row, col))) {
                UFwithoutBackWash.union(xytonum(mx, my), xytonum(row, col));
                UF.union(xytonum(mx, my), xytonum(row, col));
            }
        }

    } // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException("Out of the bound");
        }
        return grid[row][col];
    } // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= N || col >= N) {
            throw new IndexOutOfBoundsException("Out of the bound");
        }
        return UFwithoutBackWash.connected(xytonum(row, col), N * N);
    } // is the site (row, col) full?

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    } // number of open sites

    public boolean percolates() {
        return UF.connected(N * N, N * N + 1);
    } // does the system percolate?

    public static void main(String[] args) {

    } // use for unit testing (not required)

}
