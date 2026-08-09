class Solution {
    //another ai assisted soln
    private int n;
    private int[] suffix;
    private int[][] memo;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        memo = new int[n][2 * n + 1]; // M can be at most n
        return dfs(0, 1);
    }

    private int dfs(int i, int M) {
        if (i >= n) return 0;
        if (memo[i][M] != 0) return memo[i][M];
        int maxStones = 0;
        // X from 1 to 2M, but cannot exceed remaining piles
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {
            int take = suffix[i] - suffix[i + X]; // sum of piles[i..i+X-1]
            int remaining = suffix[i + X];
            int opponent = dfs(i + X, Math.max(M, X));
            int cur = take + (remaining - opponent);
            if (cur > maxStones) maxStones = cur;
        }
        memo[i][M] = maxStones;
        return maxStones;
    }
}
