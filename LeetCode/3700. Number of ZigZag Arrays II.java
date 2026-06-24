class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;          // number of possible values
        if (n == 1) return m % MOD;

        int S = 2 * m;              // states: 0..m-1 = down, m..2m-1 = up
        long[][] T = new long[S][S];

        // Build transition matrix
        for (int x = 0; x < m; x++) {
            // From down state with value x (0-index), next must be up with value y > x
            for (int y = x + 1; y < m; y++) {
                T[x][m + y] = 1;
            }
            // From up state with value x, next must be down with value y < x
            for (int y = 0; y < x; y++) {
                T[m + x][y] = 1;
            }
        }

        // Initial vector for length 2:
        // down[value] = number of previous values greater than value
        // up[value]   = number of previous values smaller than value
        long[] v0 = new long[S];
        for (int i = 0; i < m; i++) {
            v0[i] = (m - i - 1) % MOD;          // down
            v0[m + i] = i % MOD;                // up
        }

        if (n == 2) {
            long sum = 0;
            for (long x : v0) sum = (sum + x) % MOD;
            return (int) sum;
        }

        long[][] pow = matrixPow(T, n - 2);     // T^(n-2)
        long[] res = matrixVecMul(pow, v0);     // result = T^(n-2) * v0

        long ans = 0;
        for (long x : res) ans = (ans + x) % MOD;
        return (int) ans;
    }

    private long[][] matrixPow(long[][] mat, long exp) {
        int S = mat.length;
        long[][] res = identity(S);
        long[][] base = mat;
        while (exp > 0) {
            if ((exp & 1) == 1) res = matrixMul(res, base);
            base = matrixMul(base, base);
            exp >>= 1;
        }
        return res;
    }

    private long[][] identity(int S) {
        long[][] I = new long[S][S];
        for (int i = 0; i < S; i++) I[i][i] = 1;
        return I;
    }

    private long[][] matrixMul(long[][] A, long[][] B) {
        int S = A.length;
        long[][] C = new long[S][S];
        for (int i = 0; i < S; i++) {
            for (int k = 0; k < S; k++) {
                if (A[i][k] == 0) continue;
                long aik = A[i][k];
                for (int j = 0; j < S; j++) {
                    if (B[k][j] != 0) {
                        C[i][j] = (C[i][j] + aik * B[k][j]) % MOD;
                    }
                }
            }
        }
        return C;
    }

    private long[] matrixVecMul(long[][] A, long[] v) {
        int S = A.length;
        long[] res = new long[S];
        for (int i = 0; i < S; i++) {
            long sum = 0;
            for (int j = 0; j < S; j++) {
                if (A[i][j] != 0) {
                    sum = (sum + A[i][j] * v[j]) % MOD;
                }
            }
            res[i] = sum;
        }
        return res;
    }
}
