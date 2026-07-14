class Solution {
    private static final int MOD = 1_000_000_007;
    //took help from deepseek
    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int x : nums) if (x > maxVal) maxVal = x;

        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; // both subsequences empty

        for (int x : nums) {
            int[][] ndp = new int[maxVal + 1][maxVal + 1];
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    int cur = dp[g1][g2];
                    if (cur == 0) continue;

                    // 1. Skip this element
                    ndp[g1][g2] = (ndp[g1][g2] + cur) % MOD;

                    // 2. Put in seq1
                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    ndp[ng1][g2] = (ndp[ng1][g2] + cur) % MOD;

                    // 3. Put in seq2
                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    ndp[g1][ng2] = (ndp[g1][ng2] + cur) % MOD;
                }
            }
            dp = ndp;
        }

        long ans = 0;
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        return (int) ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
