//um
//today also i had to take help from deepseek, i couldnt do this at all
import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        // build segments of consecutive identical characters
        List<int[]> seg = new ArrayList<>();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int st = i;
            while (i < n && s.charAt(i) == c) i++;
            seg.add(new int[]{c - '0', i - st, st, i - 1}); // type, len, start, end
        }
        int m = seg.size();
        int[] type = new int[m], len = new int[m], start = new int[m], end = new int[m];
        for (int j = 0; j < m; j++) {
            int[] se = seg.get(j);
            type[j] = se[0]; len[j] = se[1]; start[j] = se[2]; end[j] = se[3];
        }

        // gain[i] for interior 1-segments
        int[] gain = new int[m];
        for (int j = 0; j < m; j++) {
            if (type[j] == 1 && j > 0 && j < m - 1) {
                gain[j] = len[j - 1] + len[j + 1];
            } else {
                gain[j] = Integer.MIN_VALUE;
            }
        }

        // sparse table for range maximum query on gain
        int log = 0;
        while ((1 << log) <= m) log++;
        int[][] st = new int[log][m];
        for (int j = 0; j < m; j++) st[0][j] = gain[j];
        for (int k = 1; k < log; k++) {
            int step = 1 << (k - 1);
            for (int j = 0; j + (1 << k) <= m; j++) {
                st[k][j] = Math.max(st[k - 1][j], st[k - 1][j + step]);
            }
        }
        int[] logs = new int[m + 1];
        for (int j = 2; j <= m; j++) logs[j] = logs[j / 2] + 1;

        // prefix sum of ones
        int[] pref = new int[n + 1];
        for (int j = 0; j < n; j++) pref[j + 1] = pref[j] + (s.charAt(j) - '0');
        int totalOnes = pref[n];

        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            int Lseg = find(start, l, m);
            int Rseg = find(start, r, m);
            int maxGain = 0;

            if (Lseg != Rseg) {
                // boundary: substring starts with zero -> possible first 1-run
                if (type[Lseg] == 0) {
                    int first1 = Lseg + 1;
                    if (first1 <= Rseg) {
                        int left0 = end[Lseg] - l + 1;
                        if (first1 < Rseg) {
                            int right0 = (first1 + 1 == Rseg) ? r - start[Rseg] + 1 : len[first1 + 1];
                            maxGain = Math.max(maxGain, left0 + right0);
                        }
                    }
                }
                // boundary: substring ends with zero -> possible last 1-run
                if (type[Rseg] == 0) {
                    int last1 = Rseg - 1;
                    if (last1 >= Lseg) {
                        int right0 = r - start[Rseg] + 1;
                        if (last1 > Lseg) {
                            int left0 = (last1 - 1 == Lseg) ? end[Lseg] - l + 1 : len[last1 - 1];
                            maxGain = Math.max(maxGain, left0 + right0);
                        }
                    }
                }
                // fully enclosed interior 1-segments
                int a = (start[Lseg] >= l) ? Lseg : Lseg + 1;
                int b = (end[Rseg] <= r) ? Rseg : Rseg - 1;
                if (a <= b) {
                    int left = a + 1, right = b - 1;
                    if (left <= right) {
                        int k = logs[right - left + 1];
                        int mx = Math.max(st[k][left], st[k][right - (1 << k) + 1]);
                        maxGain = Math.max(maxGain, mx);
                    }
                }
            }
            ans.add(totalOnes + maxGain);
        }
        return ans;
    }

    private int find(int[] start, int pos, int m) {
        int lo = 0, hi = m - 1;
        while (lo < hi) {
            int mid = (lo + hi + 1) / 2;
            if (start[mid] <= pos) lo = mid;
            else hi = mid - 1;
        }
        return lo;
    }
}
// a trade lets you turn "0-1-0" into "1-1-1", gaining the two zero runs.
// so we just need max(left0+right0) for any 1-run fully flanked by zeros in the substring.
// preprocess segments + sparse table for O(1) range max on interior runs, handle boundaries.
