class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long total = 0;
        //compute row sums
        long[] rowSum = new long[m];
        for (int i = 0; i < m; i++) {
            long s = 0;
            for (int j = 0; j < n; j++) {
                s += grid[i][j];
            }
            rowSum[i] = s;
            total += s;
        }
        //if total is odd, impossible
        if (total % 2 != 0) return false;
        long half = total / 2;
        //horizontal cut: check prefix of rows
        long prefix = 0;
        for (int i = 0; i < m - 1; i++) {
            prefix += rowSum[i];
            if (prefix == half) return true;
        }
        //compute column sums
        long[] colSum = new long[n];
        for (int j = 0; j < n; j++) {
            long s = 0;
            for (int i = 0; i < m; i++) {
                s += grid[i][j];
            }
            colSum[j] = s;
        }
        //vertical cut: check prefix of columns
        prefix = 0;
        for (int j = 0; j < n - 1; j++) {
            prefix += colSum[j];
            if (prefix == half) return true;
        }
        return false;
    }
}
