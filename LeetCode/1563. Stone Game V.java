class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        if (n == 1) return 0;

        // prefix sums for O(1) range sum queries
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + stoneValue[i];
        }

        // dp[l][r] = max score Alice can get from stoneValue[l..r]
        int[][] dp = new int[n][n];

        // interval length from 2 to n
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                int total = pref[r + 1] - pref[l];
                int best = 0;

                // try all possible splits k (l <= k < r)
                for (int k = l; k < r; k++) {
                    int left = pref[k + 1] - pref[l];
                    int right = total - left;

                    int score;
                    if (left > right) {
                        // Bob discards left, Alice keeps right
                        score = right + dp[k + 1][r];
                    } else if (left < right) {
                        // Bob discards right, Alice keeps left
                        score = left + dp[l][k];
                    } else {
                        // Equal: Alice can choose which side to keep
                        score = left + Math.max(dp[l][k], dp[k + 1][r]);
                    }

                    if (score > best) best = score;
                }

                dp[l][r] = best;
            }
        }

        return dp[0][n - 1];
    }
}
