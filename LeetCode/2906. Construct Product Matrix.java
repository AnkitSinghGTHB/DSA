class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int total = n * m;
        int[] flat = new int[total];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                flat[idx++] = grid[i][j] % 12345; //take mod to keep values small
            }
        }

        long[] prefix = new long[total + 1];
        prefix[0] = 1;
        for (int i = 0; i < total; i++) {
            prefix[i + 1] = (prefix[i] * flat[i]) % 12345;
        }

        long[] suffix = new long[total + 1];
        suffix[total] = 1;
        for (int i = total - 1; i >= 0; i--) {
            suffix[i] = (suffix[i + 1] * flat[i]) % 12345;
        }

        int[][] ans = new int[n][m];
        idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                long val = (prefix[idx] * suffix[idx + 1]) % 12345;
                ans[i][j] = (int) val;
                idx++;
            }
        }
        return ans;
    }
}
