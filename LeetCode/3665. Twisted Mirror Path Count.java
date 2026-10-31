class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int INVALID = -1;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // destRight[i][j] = cell reached if the robot enters (i,j) while moving right
        // destDown[i][j]  = cell reached if the robot enters (i,j) while moving down
        int[][] destRight = new int[m][n];
        int[][] destDown = new int[m][n];

        // Process from bottom-right to top-left.
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    int code = i * n + j;
                    destRight[i][j] = code;
                    destDown[i][j] = code;
                } else {
                    // Mirror: entering while moving right -> go below it, now moving down
                    if (i + 1 < m) {
                        destRight[i][j] = destDown[i + 1][j];
                    } else {
                        destRight[i][j] = INVALID;
                    }

                    // Mirror: entering while moving down -> go right of it, now moving right
                    if (j + 1 < n) {
                        destDown[i][j] = destRight[i][j + 1];
                    } else {
                        destDown[i][j] = INVALID;
                    }
                }
            }
        }

        int[] dp = new int[m * n];
        dp[0] = 1; // start at (0,0)

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) continue;

                int cur = i * n + j;
                int ways = dp[cur];
                if (ways == 0) continue;

                // Move right
                if (j + 1 < n) {
                    int dest = destRight[i][j + 1];
                    if (dest != INVALID) {
                        dp[dest] = (dp[dest] + ways) % MOD;
                    }
                }

                // Move down
                if (i + 1 < m) {
                    int dest = destDown[i + 1][j];
                    if (dest != INVALID) {
                        dp[dest] = (dp[dest] + ways) % MOD;
                    }
                }
            }
        }

        return dp[(m - 1) * n + (n - 1)];
    }
}
