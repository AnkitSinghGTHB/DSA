class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        final int NEG = -1_000_000_000;
        // dp for previous row: for each column j, array of size k+1
        int[][] top = new int[n][k + 1];
        for (int j = 0; j < n; j++) Arrays.fill(top[j], NEG);
        
        // first row
        int[][] firstRow = new int[n][k + 1];
        for (int j = 0; j < n; j++) Arrays.fill(firstRow[j], NEG);
        firstRow[0][0] = 0; // start cell (0,0) with value 0, cost 0, score 0
        for (int j = 1; j < n; j++) {
            int val = grid[0][j];
            int cost = (val == 0 ? 0 : 1);
            int score = val;
            for (int c = 0; c <= k; c++) {
                if (firstRow[j - 1][c] != NEG) {
                    int nc = c + cost;
                    if (nc <= k) {
                        firstRow[j][nc] = Math.max(firstRow[j][nc], firstRow[j - 1][c] + score);
                    }
                }
            }
        }
        top = firstRow; // now top holds dp for row 0
        
        // process remaining rows
        for (int i = 1; i < m; i++) {
            int[][] cur = new int[n][k + 1];
            for (int j = 0; j < n; j++) Arrays.fill(cur[j], NEG);
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                int cost = (val == 0 ? 0 : 1);
                int score = val;
                // from top (previous row)
                for (int c = 0; c <= k; c++) {
                    if (top[j][c] != NEG) {
                        int nc = c + cost;
                        if (nc <= k) {
                            cur[j][nc] = Math.max(cur[j][nc], top[j][c] + score);
                        }
                    }
                }
                // from left (same row)
                if (j > 0) {
                    for (int c = 0; c <= k; c++) {
                        if (cur[j - 1][c] != NEG) {
                            int nc = c + cost;
                            if (nc <= k) {
                                cur[j][nc] = Math.max(cur[j][nc], cur[j - 1][c] + score);
                            }
                        }
                    }
                }
            }
            top = cur; // move to next row
        }
        
        int ans = NEG;
        for (int c = 0; c <= k; c++) {
            ans = Math.max(ans, top[n - 1][c]);
        }
        return ans == NEG ? -1 : ans;
    }
}
