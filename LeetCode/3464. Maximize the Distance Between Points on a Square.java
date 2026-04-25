class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long P = 4L * side;
        long[] t = new long[n];
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (y == 0) {
                t[i] = x;
            } else if (x == side) {
                t[i] = side + y;
            } else if (y == side) {
                t[i] = 3L * side - x;
            } else { // x == 0
                t[i] = 4L * side - y;
            }
        }
        Arrays.sort(t);
        long[] t2 = new long[2 * n];
        for (int i = 0; i < n; i++) {
            t2[i] = t[i];
            t2[i + n] = t[i] + P;
        }

        int lo = 0, hi = side, ans = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (canSelect(t2, n, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean canSelect(long[] t2, int n, int k, int d) {
        long dlong = d;
        for (int start = 0; start < n; start++) {
            long cur = t2[start];
            int cnt = 1;
            int pos = start;
            for (int step = 1; step < k; step++) {
                int lo = pos + 1, hi = start + n - 1;
                int next = -1;
                while (lo <= hi) {
                    int m = (lo + hi) >>> 1;
                    if (t2[m] >= cur + dlong) {
                        next = m;
                        hi = m - 1;
                    } else {
                        lo = m + 1;
                    }
                }
                if (next == -1 || next >= start + n) break;
                cur = t2[next];
                pos = next;
                cnt++;
            }
            if (cnt == k && (t2[start] + (t2[start + n] - t2[start]) - cur) >= dlong) {
                return true;
            }
        }
        return false;
    }
}
