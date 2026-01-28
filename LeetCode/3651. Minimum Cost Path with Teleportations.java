//this solution was ai assisted
class Solution {
    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        long[][][] dp = new long[k + 1][m][n];
        // Initialize with large values
        for (int t = 0; t <= k; t++) {
            for (int i = 0; i < m; i++) {
                Arrays.fill(dp[t][i], Long.MAX_VALUE);
            }
        }
        dp[0][0][0] = 0; // start at (0,0) with cost 0

        // Prepare list of cells sorted by value descending
        int total = m * n;
        int[][] cells = new int[total][3];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells[idx][0] = grid[i][j];
                cells[idx][1] = i;
                cells[idx][2] = j;
                idx++;
            }
        }
        Arrays.sort(cells, (a, b) -> b[0] - a[0]); // descending by value

        // DP for each number of teleports used
        for (int t = 0; t <= k; t++) {
            if (t > 0) {
                // Teleport from states that used t-1 teleports
                long minCostGreater = Long.MAX_VALUE;
                int prevVal = -1;
                List<int[]> group = new ArrayList<>();
                for (int[] cell : cells) {
                    int val = cell[0];
                    if (val != prevVal) {
                        // process previous group if any
                        if (!group.isEmpty()) {
                            long minInGroup = Long.MAX_VALUE;
                            for (int[] c : group) {
                                int i = c[1], j = c[2];
                                if (dp[t - 1][i][j] < minInGroup) minInGroup = dp[t - 1][i][j];
                            }
                            long candidate = Math.min(minCostGreater, minInGroup);
                            for (int[] c : group) {
                                int i = c[1], j = c[2];
                                if (candidate < dp[t][i][j]) dp[t][i][j] = candidate;
                            }
                            // update minCostGreater for next groups (with lower value)
                            if (minInGroup < minCostGreater) minCostGreater = minInGroup;
                            group.clear();
                        }
                        prevVal = val;
                    }
                    group.add(cell);
                }
                // process last group
                if (!group.isEmpty()) {
                    long minInGroup = Long.MAX_VALUE;
                    for (int[] c : group) {
                        int i = c[1], j = c[2];
                        if (dp[t - 1][i][j] < minInGroup) minInGroup = dp[t - 1][i][j];
                    }
                    long candidate = Math.min(minCostGreater, minInGroup);
                    for (int[] c : group) {
                        int i = c[1], j = c[2];
                        if (candidate < dp[t][i][j]) dp[t][i][j] = candidate;
                    }
                }
            }

            // Normal moves (right and down)
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0) continue;
                    long best = dp[t][i][j];
                    if (i > 0 && dp[t][i - 1][j] != Long.MAX_VALUE) {
                        long candidate = dp[t][i - 1][j] + grid[i][j];
                        if (candidate < best) best = candidate;
                    }
                    if (j > 0 && dp[t][i][j - 1] != Long.MAX_VALUE) {
                        long candidate = dp[t][i][j - 1] + grid[i][j];
                        if (candidate < best) best = candidate;
                    }
                    dp[t][i][j] = best;
                }
            }
        }

        // Answer is the minimum cost to reach (m-1,n-1) using at most k teleports
        long ans = Long.MAX_VALUE;
        for (int t = 0; t <= k; t++) {
            if (dp[t][m - 1][n - 1] < ans) {
                ans = dp[t][m - 1][n - 1];
            }
        }
        return (int) ans;
    }
}
