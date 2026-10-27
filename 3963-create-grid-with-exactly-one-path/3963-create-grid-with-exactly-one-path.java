import java.util.*;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        for (char[] row : grid) Arrays.fill(row, '#');

        int i = 0, j = 0;
        grid[0][0] = '.';
        boolean goRight = true;

        while (i < m - 1 || j < n - 1) {
            if (goRight && j < n - 1) {
                j++;
            } else if (!goRight && i < m - 1) {
                i++;
            } else if (j < n - 1) {
                j++;
            } else {
                i++;
            }
            grid[i][j] = '.';
            goRight = !goRight;
        }

        String[] result = new String[m];
        for (int r = 0; r < m; r++) result[r] = new String(grid[r]);
        return result;
    }
}