package mahajan.prateek.preparation.recursion;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by: pramahajan on 6/26/20 4:31 PM GMT+05:30
 */
public class SudokoSolver {
    private int n = 9;
    private boolean solved = false;
    private Map<Integer, P> map;
    private char[][] a;

    public void solveSudoku(char[][] b) {
        a = b;
        map = new HashMap<>();

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (a[i][j] != '.') {
                    int k = a[i][j]-'0';
                    if (!map.containsKey(k)) {
                        map.put(k, new P(i, j, sq(i,j)));
                    } else {
                        P entry = map.get(k);

                        Set<Integer> rows = entry.rows;
                        Set<Integer> cols = entry.cols;
                        Set<Integer> sqs = entry.sqs; // squares

                        int s = sq(i,j);
                        rows.add(i);
                        cols.add(j);
                        sqs.add(s);
                    }
                }
            }
        }

        solve(0,0);

    }

    private static class P {
        Set<Integer> rows;
        Set<Integer> cols;
        Set<Integer> sqs;

        P(int i, int j, int s) {
            rows = new HashSet<>();
            rows.add(i);

            cols = new HashSet<>();
            cols.add(j);

            sqs = new HashSet<>();
            sqs.add(s);
        }
    }

    private static int sq(int i, int j) { // REVISIT - get square number (had to draw diagram)
        int x = i/3;
        int y = j/3;

        return 3*x+y;
    }

    private void solve(int x, int y) {
        if (x==n) {
            solved = true;
            return;
        }
        
        if (a[x][y]=='.') {
            for (int k=1;k<=n;k++) {
                if (valid(x,y, map, k)) {
                    a[x][y] = (char)(k+'0');
                    next(x, y);
                    
                    if (!solved) {
                        a[x][y] = '.';
                        del(x,y,map,k);
                    }
                    
                }
            }
        } else {
            next(x, y);
        }

    }

    private void next(int x, int y) {
        if (y == n-1) {
            solve(x+1, 0);
        } else {
            solve(x, y+1);
        }
    }

    private boolean valid(int i, int j, Map<Integer, P> map, int k) {
        if (!map.containsKey(k)) {
            map.put(k, new P(i, j, sq(i,j)));
        } else {
            P entry = map.get(k);

            Set<Integer> rows = entry.rows;
            Set<Integer> cols = entry.cols;
            Set<Integer> sqs = entry.sqs; // squares

            int s = sq(i,j);

            if (rows.contains(i) || cols.contains(j) || sqs.contains(s)) {
                return false;
            }

            rows.add(i);
            cols.add(j);
            sqs.add(s);
        }

        return true;
    }

    private void del(int i, int j, Map<Integer, P> map, int k) {
        P entry = map.get(k);

        Set<Integer> rows = entry.rows;
        Set<Integer> cols = entry.cols;
        Set<Integer> sqs = entry.sqs; // squares

        int s = sq(i,j);

        rows.remove(i);
        cols.remove(j);
        sqs.remove(s);
    }

    @Test
    public void test() {
        char[][] a  = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(a);
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }

}
