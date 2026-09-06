class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;  // empty t can be matched by empty subsequence

        for (int i = 1; i <= m; i++) {
            char sc = s.charAt(i - 1);
            // iterate backwards so dp[j-1] represents previous row
            for (int j = n; j >= 1; j--) {
                if (sc == t.charAt(j - 1)) {
                    // dp[j] = dp[j] + dp[j-1], cap at Integer.MAX_VALUE
                    long sum = (long) dp[j] + dp[j - 1];
                    dp[j] = sum > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) sum;
                }
            }
        }
        return dp[n];
    }
}
