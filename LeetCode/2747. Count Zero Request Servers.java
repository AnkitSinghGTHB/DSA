class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        //n servers, logs [server, time], queries q
        //each query, interval [q-x, q] inclusive
        //have to count srvrs tht didnt receive any req in that intervl
        //sort logs by time
        //for each q, find the range of log indxs whos time is in [L, R]
        //then check quers offln by inc right end
        int m = logs.length;
        int q = queries.length;
        int[][] sortedLogs = logs.clone();
        Arrays.sort(sortedLogs, (a, b) -> Integer.compare(a[1], b[1]));

        int[] times = new int[m];
        int[] servers = new int[m];
        for (int i = 0; i < m; i++) {
            servers[i] = sortedLogs[i][0];
            times[i] = sortedLogs[i][1];
        }

        int[] leftPos = new int[q];
        int[] rightPos = new int[q];

        for (int i = 0; i < q; i++) {
            long L = (long) queries[i] - x;
            long R = queries[i];

            leftPos[i] = lowerBound(times, L);
            rightPos[i] = upperBound(times, R) - 1;
        }

        Integer[] order = new Integer[q];
        for (int i = 0; i < q; i++) order[i] = i;

        Arrays.sort(order, (a, b) -> Integer.compare(rightPos[a], rightPos[b]));

        Fenwick bit = new Fenwick(m);
        int[] last = new int[n + 1];
        Arrays.fill(last, -1);

        int[] distinct = new int[q];
        int processed = -1;

        for (int qi : order) {
            int r = rightPos[qi];
            if (r < 0) continue;

            while (processed < r) {
                processed++;
                int server = servers[processed];

                if (last[server] != -1) {
                    bit.add(last[server] + 1, -1);
                }

                bit.add(processed + 1, 1);
                last[server] = processed;
            }

            int l = leftPos[qi];
            if (l <= r) {
                distinct[qi] = bit.query(r + 1) - bit.query(l); //distinct servers in servers[l...r]
            }
        }

        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            ans[i] = n - distinct[i];
        }
        return ans;
    }

    private int lowerBound(int[] arr, long target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private int upperBound(int[] arr, long target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private static class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 1];
        }

        void add(int idx, int delta) {
            while (idx < tree.length) {
                tree[idx] += delta;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }
}
