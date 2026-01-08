class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[][] dp = new int[n1 + 1][n2 + 1];
        //init base cases
        dp[0][0] = 0;
        for (int i = 1; i <= n1; i++) {
            dp[i][0] = Integer.MIN_VALUE;
        }
        for (int j = 1; j <= n2; j++) {
            dp[0][j] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                int product = nums1[i - 1] * nums2[j - 1];
                //opt 1: pair current elements, optionally with previous subsequence
                int candidate = product + Math.max(0, dp[i - 1][j - 1]);
                //opt 2: skip current element of nums1
                //opt 3: skip current element of nums2
                dp[i][j] = Math.max(candidate, Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[n1][n2];
    }
}
