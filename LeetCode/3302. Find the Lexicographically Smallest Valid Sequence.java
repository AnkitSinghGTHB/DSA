//i couldnt solve this myself, i had to take help from deepseek

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        char[] w1 = word1.toCharArray(), w2 = word2.toCharArray();
        final int INF = 1_000_000_000;

        // nextPos[i][c] = first index >= i with char c, or INF
        int[][] nextPos = new int[n + 1][26];
        for (int c = 0; c < 26; c++) nextPos[n][c] = INF;
        for (int i = n - 1; i >= 0; i--) {
            for (int c = 0; c < 26; c++) nextPos[i][c] = nextPos[i + 1][c];
            nextPos[i][w1[i] - 'a'] = i;
        }

        // dp0[i] = max suffix length of word2 matchable exactly in word1[i..]
        // dp1[i] = max suffix length of word2 matchable with at most one mismatch in word1[i..]
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            // exact
            int next0 = dp0[i + 1];
            dp0[i] = next0;
            if (next0 < m && w1[i] == w2[m - next0 - 1]) {
                dp0[i] = Math.max(dp0[i], next0 + 1);
            }
            // at most one mismatch
            int next1 = dp1[i + 1];
            dp1[i] = next1;
            // use current as exact, rest with one mismatch
            if (next1 < m && w1[i] == w2[m - next1 - 1]) {
                dp1[i] = Math.max(dp1[i], next1 + 1);
            }
            // use current as mismatch, rest exact
            if (next0 < m) {
                dp1[i] = Math.max(dp1[i], 1 + next0);
            }
        }

        // if even with one mismatch we cannot match all, return empty
        if (dp1[0] < m) return new int[0];

        int[] ans = new int[m];
        boolean used = false;
        int cur = -1;

        for (int j = 0; j < m; j++) {
            int L = m - j - 1; // remaining characters after this one

            // limit for exact match: largest i such that dp[rem][i+1] >= L
            int[] dpRem = used ? dp0 : dp1; // rem = used ? 0 : 1
            int limitExact = findLimit(dpRem, L, n);

            int limitMismatch = -1;
            if (!used) {
                // after mismatch, only exact matches left
                limitMismatch = findLimit(dp0, L, n);
            }

            int exact = INF;
            if (limitExact >= 0) {
                int pos = nextPos[cur + 1][w2[j] - 'a'];
                if (pos <= limitExact) exact = pos;
            }

            int mismatch = INF;
            if (!used && limitMismatch >= 0) {
                int best = INF;
                // find first position > cur with char != w2[j]
                for (int c = 0; c < 26; c++) {
                    if (c == w2[j] - 'a') continue;
                    int p = nextPos[cur + 1][c];
                    if (p < best) best = p;
                }
                if (best <= limitMismatch) mismatch = best;
            }

            int pick = Math.min(exact, mismatch);
            if (pick == INF) return new int[0];

            ans[j] = pick;
            if (w1[pick] != w2[j]) used = true;
            cur = pick;
        }
        return ans;
    }

    // find the largest index pos (0..n) such that dp[pos] >= L
    // dp is non-increasing with pos
    private int findLimit(int[] dp, int L, int n) {
        if (L == 0) return n - 1; // any i works
        int lo = 0, hi = n;
        while (lo < hi) {
            int mid = (lo + hi + 1) / 2;
            if (dp[mid] >= L) lo = mid;
            else hi = mid - 1;
        }
        if (dp[lo] < L) return -1;
        return lo - 1; // because we need i+1 = pos
    }
}
