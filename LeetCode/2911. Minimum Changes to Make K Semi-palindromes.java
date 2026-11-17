class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        final int INF = 1_000_000_000;

        // cost[l][r] = minimum changes to make s[l..r] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            java.util.Arrays.fill(row, INF);
        }

        // Precompute cost for every substring of length >= 2
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                int best = INF;

                // Try every divisor d of len, with 1 <= d < len
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) continue;

                    int changes = 0;

                    // Process each remainder group modulo d
                    for (int rem = 0; rem < d; rem++) {
                        int left = l + rem;
                        int last = l + rem + ((len - 1 - rem) / d) * d;
                        int right = last;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                            left += d;
                            right -= d;
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[l][r] = best;
            }
        }

        // DP: dp[i][j] = min cost to split prefix of length i into j substrings
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // last substring is s[p..i-1], length i-p must be >= 2
                for (int p = 0; p <= i - 2; p++) {
                    if (dp[p][j - 1] == INF) continue;
                    if (cost[p][i - 1] == INF) continue;

                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[p][j - 1] + cost[p][i - 1]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
