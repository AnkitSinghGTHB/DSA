class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        //init dp with -1 to indicate uncomputed
        java.util.Arrays.fill(dp, -1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, arr, d, dp));
        }
        return ans;
    }    
    private int dfs(int i, int[] arr, int d, int[] dp) {
        if (dp[i] != -1) return dp[i];
        int maxJumps = 1; //we can always visit the curr idx
        //check indices to the left
        for (int j = i - 1; j >= Math.max(0, i - d); j--) {
            if (arr[j] >= arr[i]) break; //blocked by a >= value
            maxJumps = Math.max(maxJumps, 1 + dfs(j, arr, d, dp));
        }
        //check indices to the right
        for (int j = i + 1; j <= Math.min(arr.length - 1, i + d); j++) {
            if (arr[j] >= arr[i]) break; //blocked
            maxJumps = Math.max(maxJumps, 1 + dfs(j, arr, d, dp));
        }
        dp[i] = maxJumps;
        return dp[i];
    }
}
