package mahajan.prateek.prep.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: pramahajan on 6/25/20 12:45 AM GMT+05:30
 */
public class NQueens {
        int[][] a;
        public List<List<String>> solveNQueens(int n) {
        a = new int[n][n];
        List<List<String>> ans = new ArrayList<>();
        P(0, n, ans);
        return ans;
    }

        // Point to note - try to fill in column j
        private void P(int j, int n, List<List<String>> ans) {
        if (j==n) {
            add(ans, n);
            return;
        }

        // else
        for (int i=0;i<n;i++) { // always start filling column j from row 0
            if (valid(i,j,n)) {
                a[i][j] = 1;
                P(j+1, n, ans); // go to next column and start same process from row 0
                a[i][j] = 0; // backtrack and let loop take care of next row in current column i.e. i+1,j
            }
        }
    }


        private void add(List<List<String>> ans, int n) {
        List<String> list = new ArrayList<>();
        for (int i=0;i<n;i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0;j<n;j++) {
                sb.append(a[i][j]==1 ? 'Q':'.');
            }
            list.add(sb.toString());
        }
        ans.add(list);
    }

        private boolean valid(int x, int y, int n) {
        // check columns to left in row x
        for (int j=0;j<y;j++) {
            if (a[x][j]==1) {
                return false;
            }
        }

        // no need to check same column y => there can't be any queen there

        // check diagonals
        int i1 = x, i2 = x;
        for (int j=y;j>=0;j--) {
            if (i1>=0 && a[i1][j] == 1) {
                return false;
            }
            if (i2<n && a[i2][j] == 1) {
                return false;
            }
            i1--;
            i2++;
        }

        return true;
    }

    @Test
    public void nqueens() {
        System.out.println(solveNQueens(4));
    }
}
