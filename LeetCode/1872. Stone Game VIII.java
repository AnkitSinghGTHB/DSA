//used deepseek
class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }

        long best = prefix[n]; // dp[n] = 0, so prefix[n] - dp[n] = prefix[n]

        // Compute dp[i] for i from n-1 down to 2
        for (int i = n - 1; i >= 2; i--) {
            long dp_i = best; // max over j > i of (prefix[j] - dp[j])
            best = Math.max(best, prefix[i] - dp_i);
        }

        // dp[0] = max over j >= 2 of (prefix[j] - dp[j])
        return (int) best;
    }
}
