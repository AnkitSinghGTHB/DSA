class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n + 1];
        dp[0] = 0;

        for (int j = 1; j <= n; j++) {
            long best = Long.MAX_VALUE;
            int stepCost = costs[j - 1];

            for (int d = 1; d <= 3; d++) {
                if (j - d >= 0) {
                    long cur = dp[j - d] + stepCost + (long) d * d;
                    best = Math.min(best, cur);
                }
            }
            dp[j] = best;
        }

        return (int) dp[n];
    }
}
