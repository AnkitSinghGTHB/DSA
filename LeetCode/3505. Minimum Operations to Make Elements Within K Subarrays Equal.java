import java.util.*;
//i used deepseek
class Solution {
    private static final long INF = Long.MAX_VALUE / 4;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 0;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                vals[m++] = sorted[i];
            }
        }

        Map<Integer, Integer> comp = new HashMap<>();
        for (int i = 0; i < m; i++) {
            comp.put(vals[i], i + 1); // 1-based
        }

        FenwickCount bitCnt = new FenwickCount(m);
        FenwickSum bitSum = new FenwickSum(m);

        long totalSum = 0;

        // Initial window [0, x - 1]
        for (int i = 0; i < x; i++) {
            int idx = comp.get(nums[i]);
            bitCnt.add(idx, 1);
            bitSum.add(idx, nums[i]);
            totalSum += nums[i];
        }

        int w = n - x + 1;
        long[] cost = new long[w];
        int medianPos = (x + 1) / 2; // lower median for even length

        for (int start = 0; start < w; start++) {
            int medIdx = bitCnt.kth(medianPos);
            long medVal = vals[medIdx - 1];

            long cntLeft = bitCnt.query(medIdx);
            long sumLeft = bitSum.query(medIdx);

            long cntRight = x - cntLeft;
            long sumRight = totalSum - sumLeft;

            long curCost = medVal * cntLeft - sumLeft
                         + sumRight - medVal * cntRight;

            cost[start] = curCost;

            if (start < w - 1) {
                // Remove nums[start]
                int oldVal = nums[start];
                int oldIdx = comp.get(oldVal);
                bitCnt.add(oldIdx, -1);
                bitSum.add(oldIdx, -oldVal);
                totalSum -= oldVal;

                // Add nums[start + x]
                int newVal = nums[start + x];
                int newIdx = comp.get(newVal);
                bitCnt.add(newIdx, 1);
                bitSum.add(newIdx, newVal);
                totalSum += newVal;
            }
        }

        // DP
        long[][] dp = new long[k + 1][w];

        for (int i = 0; i < w; i++) {
            dp[1][i] = cost[i];
            if (i > 0) {
                dp[1][i] = Math.min(dp[1][i], dp[1][i - 1]);
            }
        }

        for (int t = 2; t <= k; t++) {
            Arrays.fill(dp[t], INF);
            for (int i = 0; i < w; i++) {
                long best = INF;

                if (i - x >= 0) {
                    best = dp[t - 1][i - x] + cost[i];
                }

                if (i > 0) {
                    best = Math.min(best, dp[t][i - 1]);
                }

                dp[t][i] = best;
            }
        }

        return dp[k][w - 1];
    }

    // Fenwick tree for counts
    static class FenwickCount {
        int n;
        int[] tree;

        FenwickCount(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void add(int i, int delta) {
            for (; i <= n; i += i & -i) {
                tree[i] += delta;
            }
        }

        int query(int i) {
            int res = 0;
            for (; i > 0; i -= i & -i) {
                res += tree[i];
            }
            return res;
        }

        int kth(int k) {
            int idx = 0;
            int mask = Integer.highestOneBit(n);

            for (int d = mask; d > 0; d >>= 1) {
                int next = idx + d;
                if (next <= n && tree[next] < k) {
                    idx = next;
                    k -= tree[next];
                }
            }

            return idx + 1;
        }
    }

    // Fenwick tree for sums
    static class FenwickSum {
        int n;
        long[] tree;

        FenwickSum(int n) {
            this.n = n;
            tree = new long[n + 2];
        }

        void add(int i, long delta) {
            for (; i <= n; i += i & -i) {
                tree[i] += delta;
            }
        }

        long query(int i) {
            long res = 0;
            for (; i > 0; i -= i & -i) {
                res += tree[i];
            }
            return res;
        }
    }
}
