class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        //this ques was too hard for me to solve by myself
        final int MOD = 1_000_000_007;
        int[][][][] dp = new int[zero + 1][one + 1][2][limit + 1];
        //start with a single zero or a single one
        if (zero > 0) dp[1][0][0][1] = 1;
        if (one > 0) dp[0][1][1][1] = 1;
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                for (int last = 0; last < 2; last++) {
                    for (int run = 1; run <= limit; run++) {
                        int cur = dp[i][j][last][run];
                        if (cur == 0) continue;
                        //try to append a 0
                        if (i < zero) {
                            if (last == 0) {
                                if (run < limit) {
                                    dp[i + 1][j][0][run + 1] = (dp[i + 1][j][0][run + 1] + cur) % MOD;
                                }
                            } 
                            else {// last == 1
                                dp[i + 1][j][0][1] = (dp[i + 1][j][0][1] + cur) % MOD;
                            }
                        }

                        //try to append a 1
                        if (j < one) {
                            if (last == 1) {
                                if (run < limit) {
                                    dp[i][j + 1][1][run + 1] = (dp[i][j + 1][1][run + 1] + cur) % MOD;
                                }
                            } else { // last == 0
                                dp[i][j + 1][1][1] = (dp[i][j + 1][1][1] + cur) % MOD;
                            }
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int last = 0; last < 2; last++) {
            for (int run = 1; run <= limit; run++) {
                ans = (ans + dp[zero][one][last][run]) % MOD;
            }
        }
        return (int) ans;
    }
}
