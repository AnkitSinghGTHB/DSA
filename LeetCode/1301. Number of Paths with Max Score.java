import java.util.*;
//this soln is AI gen
class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        final int MOD = 1_000_000_007;
        
        // dp[i][j] = maximum sum of numeric values from S to (i,j)
        int[][] dp = new int[n][n];
        // cnt[i][j] = number of ways to achieve dp[i][j]
        long[][] cnt = new long[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // Start cell (S) at bottom-right
        dp[n-1][n-1] = 0;
        cnt[n-1][n-1] = 1;
        
        // Allowed moves: up, left, up-left
        int[] dr = {-1, 0, -1};
        int[] dc = {0, -1, -1};
        
        // Process cells from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (dp[i][j] == -1) continue;
                
                for (int d = 0; d < 3; d++) {
                    int ni = i + dr[d];
                    int nj = j + dc[d];
                    
                    if (ni < 0 || nj < 0) continue;
                    
                    char c = board.get(ni).charAt(nj);
                    if (c == 'X') continue;
                    
                    int add = 0;
                    if (c >= '1' && c <= '9') {
                        add = c - '0';
                    }
                    // 'E' contributes 0, 'S' will never be reached again
                    
                    int newSum = dp[i][j] + add;
                    
                    if (newSum > dp[ni][nj]) {
                        dp[ni][nj] = newSum;
                        cnt[ni][nj] = cnt[i][j] % MOD;
                    } else if (newSum == dp[ni][nj]) {
                        cnt[ni][nj] = (cnt[ni][nj] + cnt[i][j]) % MOD;
                    }
                }
            }
        }
        
        if (dp[0][0] == -1) {
            return new int[]{0, 0};
        }
        return new int[]{dp[0][0], (int)(cnt[0][0] % MOD)};
    }
}
