// this is one of those questions where i had to take help from deepseek or i couldnt really solve it
class Solution {
    private static class Event {
        long y;
        int type; // +1 to add, -1 to remove
        int l, r; // indices in compressed x coordinates (inclusive range [l, r])
        Event(long y, int type, int l, int r) {
            this.y = y;
            this.type = type;
            this.l = l;
            this.r = r;
        }
    }

    private static class Segment {
        double start, end;
        double L; // covered x-length
        double area;
        Segment(double start, double end, double L, double area) {
            this.start = start;
            this.end = end;
            this.L = L;
            this.area = area;
        }
    }

    // Segment tree to maintain union length of active intervals on x-axis
    private static class SegTree {
        double[] len;   // covered length in node's interval
        int[] cnt;      // number of intervals fully covering this node
        double[] span;  // total length of node's interval
        int n;

        SegTree(List<Long> xUnique) {
            int m = xUnique.size();
            this.n = m - 1; // number of elementary intervals between x points
            len = new double[4 * n];
            cnt = new int[4 * n];
            span = new double[4 * n];
            build(1, 0, n-1, xUnique);
        }

        private void build(int node, int left, int right, List<Long> xUnique) {
            if (left == right) {
                span[node] = (double)(xUnique.get(left+1) - xUnique.get(left));
                len[node] = 0.0;
                return;
            }
            int mid = (left + right) / 2;
            build(node*2, left, mid, xUnique);
            build(node*2+1, mid+1, right, xUnique);
            span[node] = span[node*2] + span[node*2+1];
        }

        public void update(int l, int r, int val) {
            update(1, 0, n-1, l, r, val);
        }

        private void update(int node, int left, int right, int l, int r, int val) {
            if (l <= left && right <= r) {
                cnt[node] += val;
                if (cnt[node] > 0) {
                    len[node] = span[node];
                } else {
                    if (left == right) {
                        len[node] = 0.0;
                    } else {
                        len[node] = len[node*2] + len[node*2+1];
                    }
                }
                return;
            }
            int mid = (left + right) / 2;
            if (l <= mid) {
                update(node*2, left, mid, l, r, val);
            }
            if (r > mid) {
                update(node*2+1, mid+1, right, l, r, val);
            }
            if (cnt[node] > 0) {
                len[node] = span[node];
            } else {
                len[node] = len[node*2] + len[node*2+1];
            }
        }

        public double getLength() {
            return len[1];
        }
    }

    public double separateSquares(int[][] squares) {
        int n = squares.length;
        // collect all distinct x coordinates
        Set<Long> xSet = new HashSet<>();
        for (int[] sq : squares) {
            long xi = sq[0];
            long li = sq[2];
            xSet.add(xi);
            xSet.add(xi + li);
        }
        List<Long> xList = new ArrayList<>(xSet);
        Collections.sort(xList);
        List<Long> xUnique = new ArrayList<>(xList); // already sorted, unique

        // map each square to compressed x indices
        Map<Long, Integer> xIndex = new HashMap<>();
        for (int i = 0; i < xUnique.size(); i++) {
            xIndex.put(xUnique.get(i), i);
        }

        // create events
        List<Event> events = new ArrayList<>();
        for (int[] sq : squares) {
            long xi = sq[0];
            long yi = sq[1];
            long li = sq[2];
            int l = xIndex.get(xi);
            int r = xIndex.get(xi + li); // note: r is index of right endpoint
            // the interval covers elementary segments from l to r-1
            events.add(new Event(yi, 1, l, r-1));
            events.add(new Event(yi + li, -1, l, r-1));
        }

        // sort events by y
        events.sort((a, b) -> Long.compare(a.y, b.y));

        SegTree segTree = new SegTree(xUnique);

        int idx = 0;
        int m = events.size();
        double prevY = events.get(0).y;
        // process all events at the first y
        while (idx < m && events.get(idx).y == prevY) {
            Event e = events.get(idx);
            segTree.update(e.l, e.r, e.type);
            idx++;
        }
        double currentL = segTree.getLength();

        List<Segment> segments = new ArrayList<>();
        while (idx < m) {
            double currY = events.get(idx).y;
            double delta = currY - prevY;
            if (delta > 0) {
                double areaSeg = currentL * delta;
                segments.add(new Segment(prevY, currY, currentL, areaSeg));
            }
            // process all events at currY
            while (idx < m && events.get(idx).y == currY) {
                Event e = events.get(idx);
                segTree.update(e.l, e.r, e.type);
                idx++;
            }
            prevY = currY;
            currentL = segTree.getLength();
        }

        // If no segments (should not happen with positive area), return 0
        if (segments.isEmpty()) {
            return 0.0;
        }

        // Build prefix sums and find the target half area
        int segCnt = segments.size();
        double[] yVals = new double[segCnt + 1];
        double[] Lvals = new double[segCnt];
        double[] prefix = new double[segCnt + 1];
        yVals[0] = segments.get(0).start;
        for (int i = 0; i < segCnt; i++) {
            Segment seg = segments.get(i);
            yVals[i] = seg.start;
            yVals[i + 1] = seg.end;
            Lvals[i] = seg.L;
            prefix[i + 1] = prefix[i] + seg.area;
        }
        double totalArea = prefix[segCnt];
        double target = totalArea / 2.0;

        final double EPS = 1e-12;
        for (int i = 0; i < segCnt; i++) {
            if (Math.abs(prefix[i] - target) < EPS) {
                return yVals[i];
            }
            if (prefix[i] < target && target < prefix[i + 1]) {
                double h = yVals[i] + (target - prefix[i]) / Lvals[i];
                return h;
            }
        }
        // If target equals the very end (should not happen)
        if (Math.abs(prefix[segCnt] - target) < EPS) {
            return yVals[segCnt];
        }

        // fallback (should never be reached)
        return 0.0;
    }
}
