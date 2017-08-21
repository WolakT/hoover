package com.example.demo.dto;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Tomcio on 2017-04-02.
 */

public class Percolation {
    private Object[] grid;
    private int open;
    private WeightedQuickUnionUF uf;
    private int n;

    public Percolation(int n) {
        this.n = n;
        open = 0;
        if (n <= 0)
            throw new IllegalArgumentException("N must be greater then 0");
        else {
            uf = new WeightedQuickUnionUF(n * n);
            grid = new Object[n * n];
            for (int i = 0; i < n * n; i++) {

                grid[i] = false;
                // System.out.println(grid[i].toString() );

            }
            // creating virtual roots
            for (int i = 0; i < n; i++) {
                uf.union(0, i);
            }
            for (int i = n * n - 1; i > (n * n - 1) - n; i--) {
                uf.union(n * n - 1, i);
            }
        }
    }

    private int convert(int row, int column) {
        // System.out.println(row*N+(column-N-1));
        return row * n + (column - n - 1);
    }

    private void checkBounds(int row, int col) {
        if (row > n || row < 1 || col > n || col < 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean validate(int row, int column) {
        // System.out.println(row <= N && row >= 1 && column <= N && column >=0);
        return row <= n && row >= 1 && column <= n && column >= 1;
    }

    private void connectOpenAdjacent(int row, int column, int nrow, int ncolumn) {
        if (isOpen(nrow, ncolumn) && !uf.connected(convert(row, column), convert(nrow, ncolumn))) {
            uf.union(convert(nrow, ncolumn), convert(row, column));
        }
    }

    public void open(int row, int col) {
        try {
            checkBounds(row, col);
            if (!isOpen(row, col)) {
                grid[convert(row, col)] = true;
                open += 1;
                // check adjacent
                if (validate(row + 1, col)) connectOpenAdjacent(row, col, row + 1, col);

                if (validate(row - 1, col)) connectOpenAdjacent(row, col, row - 1, col);
                if (validate(row, col + 1)) connectOpenAdjacent(row, col, row, col + 1);
                if (validate(row, col - 1)) connectOpenAdjacent(row, col, row, col - 1);

                if (validate(row-1, col - 1)) connectOpenAdjacent(row, col, row-1, col - 1);
                if (validate(row+1, col + 1)) connectOpenAdjacent(row, col, row+1, col + 1);
                if (validate(row-1, col + 1)) connectOpenAdjacent(row, col, row-1, col + 1);
                if (validate(row+1, col - 1)) connectOpenAdjacent(row, col, row+1, col - 1);



            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int numberOfOpenSites() {
        return open;
    }

    public boolean isOpen(int row, int col) {
        boolean tr = true;
        try {
            checkBounds(row, col);
            return grid[convert(row, col)].equals(tr);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.toString());
            throw new IndexOutOfBoundsException();
        }


        // System.out.println(grid[convert(row,col)].equals(tr));

    }

    public boolean isFull(int row, int col) {
        try {
            checkBounds(row, col);
            if (isOpen(row, col)) {
                if (uf.connected(convert(row, col), convert(1, 1))) return true;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }

        return false;
    }


    public boolean percolates() {
        if (n == 1 && !isOpen(n, n)) {
            return false;
        } else if (uf.connected(convert(1, 1), convert(n, n))){
            return true;
        } else{
            for (int i = 1; i < n; i++) {
                if (uf.connected(convert(i,1),convert(i,n))){
                    return true ;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }


}
