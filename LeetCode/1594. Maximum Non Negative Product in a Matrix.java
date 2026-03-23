class Solution {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] maxProd = new long[m][n];
        long[][] minProd = new long[m][n];
        //initialize top-left cell
        maxProd[0][0] = minProd[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                long max = Long.MIN_VALUE;
                long min = Long.MAX_VALUE;
                //from top
                if (i > 0) {
                    long cand1 = maxProd[i-1][j] * grid[i][j];
                    long cand2 = minProd[i-1][j] * grid[i][j];
                    max = Math.max(max, Math.max(cand1, cand2));
                    min = Math.min(min, Math.min(cand1, cand2));
                }
                //from left
                if (j > 0) {
                    long cand1 = maxProd[i][j-1] * grid[i][j];
                    long cand2 = minProd[i][j-1] * grid[i][j];
                    max = Math.max(max, Math.max(cand1, cand2));
                    min = Math.min(min, Math.min(cand1, cand2));
                }
                maxProd[i][j] = max;
                minProd[i][j] = min;
            }
        }
        long res = maxProd[m-1][n-1];
        if (res < 0) return -1;
        return (int)(res % 1_000_000_007);
    }
}
