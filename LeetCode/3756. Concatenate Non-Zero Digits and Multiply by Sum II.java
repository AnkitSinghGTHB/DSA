class Solution {
    private static final int MOD = 1_000_000_007;
    //this is an AI assisted solution
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        // Step 1: collect non-zero digits and their positions
        int[] posList = new int[n];
        int[] digitList = new int[n];
        int len = 0; // number of non-zero digits
        int[] idxAtPos = new int[n];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                posList[len] = i;
                digitList[len] = c - '0';
                idxAtPos[i] = len;
                len++;
            } else {
                idxAtPos[i] = -1;
            }
        }

        // Step 2: prefix sums of digit values
        long[] sumPref = new long[len + 1];
        for (int i = 0; i < len; i++) {
            sumPref[i + 1] = sumPref[i] + digitList[i];
        }

        // Step 3: prefix concatenated values and powers of 10
        long[] numPref = new long[len + 1];
        long[] pow10 = new long[len + 1];
        pow10[0] = 1;
        for (int i = 1; i <= len; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }
        for (int i = 0; i < len; i++) {
            numPref[i + 1] = (numPref[i] * 10 + digitList[i]) % MOD;
        }

        // Step 4: nextPos and prevPos
        int[] nextPos = new int[n + 1];
        nextPos[n] = len; // sentinel
        for (int i = n - 1; i >= 0; i--) {
            if (idxAtPos[i] != -1) {
                nextPos[i] = idxAtPos[i];
            } else {
                nextPos[i] = nextPos[i + 1];
            }
        }

        int[] prevPos = new int[n];
        prevPos[0] = (idxAtPos[0] != -1) ? idxAtPos[0] : -1;
        for (int i = 1; i < n; i++) {
            if (idxAtPos[i] != -1) {
                prevPos[i] = idxAtPos[i];
            } else {
                prevPos[i] = prevPos[i - 1];
            }
        }

        // Step 5: answer queries
        int q = queries.length;
        int[] ans = new int[q];
        for (int qi = 0; qi < q; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];
            int start = nextPos[l];
            int end = prevPos[r];

            if (start == len || end < 0 || start > end) {
                ans[qi] = 0;
                continue;
            }

            int cnt = end - start + 1;
            // x = numPref[end+1] - numPref[start] * 10^cnt
            long x = (numPref[end + 1] - (numPref[start] * pow10[cnt]) % MOD + MOD) % MOD;
            long digitSum = sumPref[end + 1] - sumPref[start];
            ans[qi] = (int) ((x * (digitSum % MOD)) % MOD);
        }

        return ans;
    }
}
