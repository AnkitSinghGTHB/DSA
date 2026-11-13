class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] a = new int[n + 2];//1idx arr
        for (int i = 1; i <= n; i++) {
            a[i] = nums[i - 1];
        }
        a[n + 1] = 0;        
        long[] prefix = new long[n + 2];//pref sum
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + a[i];
        }
        //nxt > elem to the right
        int[] nxt = new int[n + 2];
        int[] stack = new int[n + 2];
        int top = 0;
        for (int i = n; i >= 1; i--) {
            while (top > 0 && a[stack[top - 1]] <= a[i]) {
                top--;
            }
            nxt[i] = top > 0 ? stack[top - 1] : n + 1;
            stack[top++] = i;
        }
        nxt[n + 1] = n + 1;

        //binary lifting, from here deepseek helps
        int LOG = 18; // 2^17 > 1e5
        int[][] up = new int[LOG][n + 2];
        long[][] sum = new long[LOG][n + 2];

        for (int i = 1; i <= n + 1; i++) {
            up[0][i] = nxt[i];
            sum[0][i] = (long) a[i] * (nxt[i] - i);
        }

        for (int kk = 1; kk < LOG; kk++) {
            for (int i = 1; i <= n + 1; i++) {
                int mid = up[kk - 1][i];
                up[kk][i] = up[kk - 1][mid];
                sum[kk][i] = sum[kk - 1][i] + sum[kk - 1][mid];
            }
        }

        long answer = 0;

        for (int R = 1; R <= n; R++) {
            int lo = 1, hi = R;
            int bestL = R;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (cost(mid, R, a, prefix, up, sum, LOG) <= k) {
                    bestL = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            answer += (R - bestL + 1);
        }

        return answer;
    }

    private long cost(int L, int R, int[] a, long[] prefix, int[][] up, long[][] sum, int LOG) {
        long total = 0;
        int curr = L;
        int limit = R + 1;
        for (int kk = LOG - 1; kk >= 0; kk--) {
            if (up[kk][curr] <= limit) {
                total += sum[kk][curr];
                curr = up[kk][curr];
            }
        }
        if (curr <= R) {
            total += (long) a[curr] * (R - curr + 1);
        }
        long sumA = prefix[R] - prefix[L - 1];
        return total - sumA;
    }
}
