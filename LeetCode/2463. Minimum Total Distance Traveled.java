class Solution {
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        int n = robot.size();
        int m = factory.length;
        int[] rob = new int[n];
        for (int i = 0; i < n; i++) rob[i] = robot.get(i);
        Arrays.sort(rob);
        // sort factories by position
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));
        
        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }
        for (int j = 0; j <= m; j++) dp[0][j] = 0;
        
        for (int j = 1; j <= m; j++) {
            int pos = factory[j - 1][0];
            int limit = factory[j - 1][1];
            for (int i = 1; i <= n; i++) {
                // skip this factory
                dp[i][j] = dp[i][j - 1];
                long sum = 0;
                // assign k robots to this factory (the last k robots of the first i)
                for (int k = 1; k <= limit && k <= i; k++) {
                    int idx = i - k;
                    sum += Math.abs(rob[idx] - pos);
                    long prev = dp[i - k][j - 1];
                    if (prev < Long.MAX_VALUE / 2) {
                        dp[i][j] = Math.min(dp[i][j], prev + sum);
                    }
                }
            }
        }
        return dp[n][m];
    }
}
