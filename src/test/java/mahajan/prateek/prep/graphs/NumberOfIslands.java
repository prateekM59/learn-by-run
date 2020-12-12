package mahajan.prateek.prep.graphs;

import org.junit.Test;

/**
 * Created by: pramahajan on 5/23/20 1:35 AM GMT+05:30
 */
public class NumberOfIslands {
    int called = 0;


    // Complexity - sink is called 4 times for each 1 and outer loop runs for m*n always so O(4a + mn), where a is number of 1s
    // Worst case - when all are 1s => O(5mn)
    @Test
    public void numberOfIslands() {
        char[][] grid  = {
                {'1','1', '1', '1'},
                {'1','1', '1', '1'},
                {'1','1', '1', '1'}
        };
        numIslands(grid);
    }

    private int n;
    private int m;

    public int numIslands(char[][] grid) {
        int count = 0;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                System.out.println("Outer loop: " + called++);
                if (grid[i][j] == '1') {
                    sink(grid, i, j);
                    ++count;
                }
            }


        }
        return count;
    }

    private void sink(char[][] grid, int i, int j) {
        System.out.println("Calling DFS: " + called++);
        System.out.println("i: " + i + ", j: " + j);
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] != '1') return;
        grid[i][j] = '0';
        sink(grid, i + 1, j);
        sink(grid, i - 1, j);
        sink(grid, i, j + 1);
        sink(grid, i, j - 1);
    }
}
