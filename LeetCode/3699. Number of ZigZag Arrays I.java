class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;          // number of distinct values

        // dpUp[x] = number of valid sequences ending at value x with last move UP
        // dpDown[x] = number of valid sequences ending at value x with last move DOWN
        long[] dpUp = new long[m + 2];
        long[] dpDown = new long[m + 2];

        // Initialize for sequences of length 2:
        // For each ending value x:
        //   UP means previous value < x, so there are (x-1) choices
        //   DOWN means previous value > x, so there are (m-x) choices
        for (int x = 1; x <= m; x++) {
            dpUp[x] = x - 1;
            dpDown[x] = m - x;
        }

        // If n == 2, sum all dpUp + dpDown
        if (n == 2) {
            long ans = 0;
            for (int x = 1; x <= m; x++) ans = (ans + dpUp[x] + dpDown[x]) % MOD;
            return (int) ans;
        }

        // Build sequences of length 3 .. n
        for (int len = 3; len <= n; len++) {
            long[] newUp = new long[m + 2];
            long[] newDown = new long[m + 2];

            // Prefix sums of dpDown: pref[i] = sum_{x=1..i} dpDown[x]
            long[] pref = new long[m + 1];
            for (int i = 1; i <= m; i++) {
                pref[i] = (pref[i - 1] + dpDown[i]) % MOD;
            }

            // Suffix sums of dpUp: suff[i] = sum_{x=i..m} dpUp[x]
            long[] suff = new long[m + 2];
            for (int i = m; i >= 1; i--) {
                suff[i] = (suff[i + 1] + dpUp[i]) % MOD;
            }

            for (int x = 1; x <= m; x++) {
                // To extend a sequence that ends with UP, the next value must be smaller:
                // number of previous values > x
                newDown[x] = suff[x + 1];   // sum of dpUp[prev] for prev > x

                // To extend a sequence that ends with DOWN, the next value must be larger:
                // number of previous values < x
                newUp[x] = pref[x - 1];     // sum of dpDown[prev] for prev < x
            }

            dpUp = newUp;
            dpDown = newDown;
        }

        long answer = 0;
        for (int x = 1; x <= m; x++) {
            answer = (answer + dpUp[x] + dpDown[x]) % MOD;
        }
        return (int) answer;
    }
}
