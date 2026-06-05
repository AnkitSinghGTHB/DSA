class Solution {
    public long totalWaviness(long num1, long num2) {
        return f(num2) - f(num1 - 1);
    }

    private long f(long N) {
        if (N <= 0) return 0;
        String s = Long.toString(N);
        int n = s.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) digits[i] = s.charAt(i) - '0';

        //memoization arrays: count and sum for each state
        long[][][][][] cnt = new long[n][2][2][11][11];
        long[][][][][] sum = new long[n][2][2][11][11];
        boolean[][][][][] vis = new boolean[n][2][2][11][11];

        return dfs(0, true, true, 10, 10, digits, cnt, sum, vis)[1];
    }

    private long[] dfs(int pos, boolean tight, boolean leading,
                       int prev1, int prev2, int[] digits,
                       long[][][][][] cnt, long[][][][][] sum,
                       boolean[][][][][] vis) {
        if (pos == digits.length) {
            //we dont count the number 0 (all digits were leading zeros)
            if (leading) return new long[]{0, 0};
            return new long[]{1, 0};
        }

        int t = tight ? 1 : 0;
        int l = leading ? 1 : 0;
        if (vis[pos][t][l][prev1][prev2]) {
            return new long[]{cnt[pos][t][l][prev1][prev2], sum[pos][t][l][prev1][prev2]};
        }

        long totalCount = 0;
        long totalSum = 0;
        int limit = tight ? digits[pos] : 9;

        for (int d = 0; d <= limit; d++) {
            boolean newTight = tight && (d == limit);
            boolean newLeading = leading && (d == 0);

            long add = 0;
            int newPrev1, newPrev2;
            if (!newLeading) {
                //we have a non‑leading digit; check for peak/valley
                if (prev1 != 10 && prev2 != 10) {
                    if (prev1 > prev2 && prev1 > d) add = 1;
                    else if (prev1 < prev2 && prev1 < d) add = 1;
                }
                newPrev2 = prev1;
                newPrev1 = d;
            } 
            else {
                //still in leading zeros; keep sentinel values
                newPrev1 = 10;
                newPrev2 = 10;
            }

            long[] sub = dfs(pos + 1, newTight, newLeading, newPrev1, newPrev2,
                             digits, cnt, sum, vis);
            long subCount = sub[0];
            long subSum = sub[1];

            totalCount += subCount;
            totalSum += subSum + add * subCount;
        }

        vis[pos][t][l][prev1][prev2] = true;
        cnt[pos][t][l][prev1][prev2] = totalCount;
        sum[pos][t][l][prev1][prev2] = totalSum;
        return new long[]{totalCount, totalSum};
    }
}
