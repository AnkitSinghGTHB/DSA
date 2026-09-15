class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        // isPal[l][r] = true if s[l..r] is a palindrome
        boolean[][] isPal = new boolean[n][n];
        for (int len = 1; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                if (s.charAt(l) == s.charAt(r)) {
                    if (len <= 2 || isPal[l + 1][r - 1]) {
                        isPal[l][r] = true;
                    }
                }
            }
        }

        // dp[i] = max palindromes using prefix of length i
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; // skip s[i-1]
            // consider all palindromic substrings ending at i-1 with length >= k
            for (int l = 0; l <= i - k; l++) {
                if (isPal[l][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[l] + 1);
                }
            }
        }
        return dp[n];
    }
}
