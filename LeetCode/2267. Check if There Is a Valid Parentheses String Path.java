class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int totalSteps = m + n - 1;
        // A valid parentheses string must have even length.
        if (totalSteps % 2 != 0) return false;

        int maxBalance = totalSteps;
        // dp[i][j][b] = can reach (i,j) with balance b
        boolean[][][] dp = new boolean[m][n][maxBalance + 1];

        int start = grid[0][0] == '(' ? 1 : -1;
        if (start < 0) return false;
        dp[0][0][start] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int b = 0; b <= maxBalance; b++) {
                    if (!dp[i][j][b]) continue;

                    // move right
                    if (j + 1 < n) {
                        int nb = b + (grid[i][j + 1] == '(' ? 1 : -1);
                        if (nb >= 0 && nb <= maxBalance) {
                            dp[i][j + 1][nb] = true;
                        }
                    }
                    // move down
                    if (i + 1 < m) {
                        int nb = b + (grid[i + 1][j] == '(' ? 1 : -1);
                        if (nb >= 0 && nb <= maxBalance) {
                            dp[i + 1][j][nb] = true;
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}
