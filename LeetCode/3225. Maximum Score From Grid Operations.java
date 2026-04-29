class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        if (n == 1) return 0;
        
        // prefix sums per column
        long[][] pref = new long[n][n];
        for (int j = 0; j < n; j++) {
            pref[j][0] = grid[0][j];
            for (int i = 1; i < n; i++) {
                pref[j][i] = pref[j][i-1] + grid[i][j];
            }
        }
        
        // sumCol[j][lo][hi] = sum of grid[i][j] for i in [lo, hi] (inclusive)
        long[][][] sumCol = new long[n][n+1][n+1];
        for (int j = 0; j < n; j++) {
            for (int lo = 0; lo <= n; lo++) {
                for (int hi = lo; hi <= n; hi++) {
                    if (lo == hi) sumCol[j][lo][hi] = 0;
                    else {
                        int hh = hi - 1; // inclusive
                        sumCol[j][lo][hi] = pref[j][hh] - (lo > 0 ? pref[j][lo-1] : 0);
                    }
                }
            }
        }
        
        // dpPrev[prev][cur] for columns up to j (where j is the index of cur column)
        long NEG = Long.MIN_VALUE / 4;
        long[][] dpPrev = new long[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dpPrev[i][j] = NEG;
            }
        }
        
        // initialize for j = 1 (columns 0 and 1)
        for (int h0 = 0; h0 <= n; h0++) {
            for (int h1 = 0; h1 <= n; h1++) {
                long add = 0;
                // column 0: right neighbor condition
                if (h1 > h0) {
                    add = sumCol[0][h0][h1];
                }
                dpPrev[h0][h1] = add;
            }
        }
        
        // process columns 1 to n-2 (as middle columns)
        for (int j = 1; j <= n-2; j++) {
            long[][] dpCurr = new long[n+1][n+1];
            for (int i = 0; i <= n; i++) {
                for (int k = 0; k <= n; k++) {
                    dpCurr[i][k] = NEG;
                }
            }
            for (int prev = 0; prev <= n; prev++) {
                for (int cur = 0; cur <= n; cur++) {
                    long val = dpPrev[prev][cur];
                    if (val == NEG) continue;
                    for (int next = 0; next <= n; next++) {
                        long add = 0;
                        int mx = Math.max(prev, next);
                        if (cur < mx) {
                            add = sumCol[j][cur][mx];
                        }
                        long nxt = val + add;
                        if (nxt > dpCurr[cur][next]) {
                            dpCurr[cur][next] = nxt;
                        }
                    }
                }
            }
            dpPrev = dpCurr;
        }
        
        // last column (n-1): only left neighbor
        long ans = 0;
        for (int prev = 0; prev <= n; prev++) {
            for (int cur = 0; cur <= n; cur++) {
                long val = dpPrev[prev][cur];
                if (val == NEG) continue;
                long add = 0;
                if (prev > cur) {
                    add = sumCol[n-1][cur][prev];
                }
                long total = val + add;
                if (total > ans) ans = total;
            }
        }
        return ans;
    }
}
