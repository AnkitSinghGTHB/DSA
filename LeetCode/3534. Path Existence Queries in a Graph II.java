import java.util.*;
//i took help from ai
class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Map each value to its compressed index
        int maxVal = 0;
        for (int x : nums) if (x > maxVal) maxVal = x;
        int[] idxOfVal = new int[maxVal + 1];
        Arrays.fill(idxOfVal, -1);

        // Sort unique values
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                idxOfVal[sorted[i]] = m;
                sorted[m++] = sorted[i];
            }
        }
        int[] vals = Arrays.copyOf(sorted, m);

        // Component IDs (gaps > maxDiff split components)
        int[] comp = new int[m];
        int cid = 0;
        comp[0] = cid;
        for (int i = 1; i < m; i++) {
            if (vals[i] - vals[i - 1] > maxDiff) cid++;
            comp[i] = cid;
        }

        // rightmost[i] = farthest index reachable from i in one edge
        int[] rightmost = new int[m];
        int r = 0;
        for (int i = 0; i < m; i++) {
            if (r < i) r = i;
            while (r + 1 < m && vals[r + 1] - vals[i] <= maxDiff) r++;
            rightmost[i] = r;
        }

        // Binary lifting table
        int LOG = 1;
        while ((1 << LOG) <= m) LOG++;
        int[][] up = new int[LOG][m];
        for (int i = 0; i < m; i++) up[0][i] = rightmost[i];
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < m; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int q = queries.length;
        int[] ans = new int[q];

        for (int qi = 0; qi < q; qi++) {
            int u = queries[qi][0];
            int v = queries[qi][1];

            if (u == v) {
                ans[qi] = 0;
                continue;
            }
            if (nums[u] == nums[v]) {
                ans[qi] = 1; // direct edge
                continue;
            }

            int a = nums[u], b = nums[v];
            int ia = idxOfVal[a];
            int ib = idxOfVal[b];

            if (comp[ia] != comp[ib]) {
                ans[qi] = -1;
                continue;
            }

            if (ia > ib) {
                int tmp = ia; ia = ib; ib = tmp;
            }

            // Greedy jump from ia to ib
            int cur = ia;
            int steps = 0;
            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < ib) {
                    steps += (1 << k);
                    cur = up[k][cur];
                }
            }
            // one final jump
            ans[qi] = steps + 1;
        }

        return ans;
    }
}
