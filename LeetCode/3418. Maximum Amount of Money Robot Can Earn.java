class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length, n = coins[0].length;
        int[][] dp0 = new int[m][n];
        int[][] dp1 = new int[m][n];
        int[][] dp2 = new int[m][n];
        
        //initialize with a very small number (negative infinity)
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(dp0[i], Integer.MIN_VALUE);
            java.util.Arrays.fill(dp1[i], Integer.MIN_VALUE);
            java.util.Arrays.fill(dp2[i], Integer.MIN_VALUE);
        }
        
        //starting cell
        dp0[0][0] = coins[0][0];
        dp1[0][0] = Math.max(coins[0][0], 0);
        dp2[0][0] = Math.max(coins[0][0], 0);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                //for 0 neutralizations
                int best0 = Integer.MIN_VALUE;
                if (i > 0 && dp0[i-1][j] != Integer.MIN_VALUE)
                    best0 = Math.max(best0, dp0[i-1][j] + coins[i][j]);
                if (j > 0 && dp0[i][j-1] != Integer.MIN_VALUE)
                    best0 = Math.max(best0, dp0[i][j-1] + coins[i][j]);
                if (best0 != Integer.MIN_VALUE)
                    dp0[i][j] = best0;
                
                //for 1 neutralization (at most 1)
                int best1 = Integer.MIN_VALUE;
                // not using neutralization on current cell
                if (i > 0 && dp1[i-1][j] != Integer.MIN_VALUE)
                    best1 = Math.max(best1, dp1[i-1][j] + coins[i][j]);
                if (j > 0 && dp1[i][j-1] != Integer.MIN_VALUE)
                    best1 = Math.max(best1, dp1[i][j-1] + coins[i][j]);
                // using neutralization on current cell (requires 0 neutralizations before)
                if (i > 0 && dp0[i-1][j] != Integer.MIN_VALUE)
                    best1 = Math.max(best1, dp0[i-1][j]);
                if (j > 0 && dp0[i][j-1] != Integer.MIN_VALUE)
                    best1 = Math.max(best1, dp0[i][j-1]);
                if (best1 != Integer.MIN_VALUE)
                    dp1[i][j] = best1;
                
                //for 2 neutralizations (at most 2)
                int best2 = Integer.MIN_VALUE;
                // not using neutralization on current cell
                if (i > 0 && dp2[i-1][j] != Integer.MIN_VALUE)
                    best2 = Math.max(best2, dp2[i-1][j] + coins[i][j]);
                if (j > 0 && dp2[i][j-1] != Integer.MIN_VALUE)
                    best2 = Math.max(best2, dp2[i][j-1] + coins[i][j]);
                // using neutralization on current cell (requires at most 1 before)
                if (i > 0 && dp1[i-1][j] != Integer.MIN_VALUE)
                    best2 = Math.max(best2, dp1[i-1][j]);
                if (j > 0 && dp1[i][j-1] != Integer.MIN_VALUE)
                    best2 = Math.max(best2, dp1[i][j-1]);
                if (best2 != Integer.MIN_VALUE)
                    dp2[i][j] = best2;
            }
        }
        
        return dp2[m-1][n-1];
    }
}
