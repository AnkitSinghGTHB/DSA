class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m - k + 1][n - k + 1];
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                //collect all values in this k x k submatrix
                Set<Integer> vals = new HashSet<>();
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        vals.add(grid[x][y]);
                    }
                }
                List<Integer> list = new ArrayList<>(vals);
                Collections.sort(list);
                int minDiff = 0;
                if (list.size() > 1) {
                    minDiff = Integer.MAX_VALUE;
                    for (int t = 0; t < list.size() - 1; t++) {
                        minDiff = Math.min(minDiff, list.get(t + 1) - list.get(t));
                    }
                }
                ans[i][j] = minDiff;
            }
        }
        return ans;
    }
}
