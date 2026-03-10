class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        //another ai gen
        final int MOD = 1_000_000_007;
        int z = zero, o = one;
        long[][] dp0 = new long[z+1][o+1];
        long[][] dp1 = new long[z+1][o+1];
        
        // base cases: sequences consisting only of zeros or only of ones
        for (int i = 1; i <= z && i <= limit; i++) {
            dp0[i][0] = 1;
        }
        for (int j = 1; j <= o && j <= limit; j++) {
            dp1[0][j] = 1;
        }
        
        // prefix sums: pref1[i][j] = sum_{t=0..i} dp1[t][j]
        long[][] pref1 = new long[z+1][o+1];
        for (int j = 0; j <= o; j++) {
            pref1[0][j] = dp1[0][j];
        }
        // pref0[i][j] = sum_{t=0..j} dp0[i][t]  (will be filled row by row)
        long[][] pref0 = new long[z+1][o+1];
        
        for (int i = 0; i <= z; i++) {
            for (int j = 0; j <= o; j++) {
                if (i == 0 && j == 0) {
                    pref0[i][j] = 0;
                    continue;
                }
                // compute dp0[i][j] : last element 0
                if (i > 0) {
                    int L = Math.min(limit, i);
                    int hi = i - 1;
                    int lo = i - L - 1;
                    long sum = pref1[hi][j];
                    if (lo >= 0) sum = (sum - pref1[lo][j] + MOD) % MOD;
                    dp0[i][j] = (dp0[i][j] + sum) % MOD;
                }
                // compute dp1[i][j] : last element 1
                if (j > 0) {
                    int L = Math.min(limit, j);
                    int hi = j - 1;
                    int lo = j - L - 1;
                    long sum = pref0[i][hi];
                    if (lo >= 0) sum = (sum - pref0[i][lo] + MOD) % MOD;
                    dp1[i][j] = (dp1[i][j] + sum) % MOD;
                }
                // update pref0 for this (i,j)
                if (j == 0) {
                    pref0[i][j] = dp0[i][j];
                } else {
                    pref0[i][j] = (pref0[i][j-1] + dp0[i][j]) % MOD;
                }
            }
            // after finishing all j for this i, update pref1 row
            if (i > 0) {
                for (int j = 0; j <= o; j++) {
                    pref1[i][j] = (pref1[i-1][j] + dp1[i][j]) % MOD;
                }
            }
        }
        
        long ans = (dp0[z][o] + dp1[z][o]) % MOD;
        return (int) ans;
    }
}
