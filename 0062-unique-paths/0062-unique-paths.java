class Solution {
    public int uniquePaths(int m, int n) {

        // Create a 2D array to store the number of paths to each cell
        int[][] dp = new int[m][n];

        // There is only 1 way to reach any cell in the first row
        // (moving right)
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // There is only 1 way to reach any cell in the first column
        // (moving down)
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // Fill the rest of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                // Total paths to current cell =
                // paths from above + paths from left
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // Return the value at the bottom-right corner
        return dp[m - 1][n - 1];
    }
}