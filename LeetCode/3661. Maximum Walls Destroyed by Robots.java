import java.util.*;

class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        //  combine and sort robots by position
        int[][] robotInfo = new int[n][2];
        for (int i = 0; i < n; i++) {
            robotInfo[i][0] = robots[i];
            robotInfo[i][1] = distance[i];
        }
        Arrays.sort(robotInfo, (a, b) -> Integer.compare(a[0], b[0]));
        int[] pos = new int[n];
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = robotInfo[i][0];
            dist[i] = robotInfo[i][1];
        }

        int[] wallsSorted = walls.clone();
        Arrays.sort(wallsSorted);
        int W = wallsSorted.length;

        // self walls at robot positions
        int[] self = new int[n];
        // walls in each gap: gap 0 = (-inf, pos[0]), gap i (1..n-1) = (pos[i-1], pos[i]), gap n = (pos[n-1], +inf)
        List<Integer>[] gapWalls = new List[n + 1];
        for (int i = 0; i <= n; i++) gapWalls[i] = new ArrayList<>();

        for (int w : wallsSorted) {
            int idx = Arrays.binarySearch(pos, w);
            if (idx >= 0) {
                self[idx] = 1;               // wall at robot position
            } else {
                int ip = -idx - 1;           // insertion point
                gapWalls[ip].add(w);
            }
        }

        // pre‑compute for internal gaps (i = 1 .. n-1)
        int[] a = new int[n];   // right shot of left robot
        int[] b = new int[n];   // left shot of right robot
        int[] c = new int[n];   // union of both
        for (int i = 1; i < n; i++) {
            List<Integer> list = gapWalls[i];
            if (list.isEmpty()) {
                a[i] = b[i] = c[i] = 0;
                continue;
            }
            int leftPos = pos[i - 1];
            int rightPos = pos[i];
            int leftDist = dist[i - 1];
            int rightDist = dist[i];

            // a : walls in (leftPos, min(leftPos+leftDist, rightPos)]
            int r1 = Math.min(leftPos + leftDist, rightPos);
            a[i] = countInRange(list, leftPos + 1, r1);
            // b : walls in [max(rightPos-rightDist, leftPos), rightPos-1]
            int l2 = Math.max(rightPos - rightDist, leftPos);
            b[i] = countInRange(list, l2, rightPos - 1);
            // c : union of both intervals
            int overlapStart = Math.max(leftPos + 1, l2);
            int overlapEnd = Math.min(r1, rightPos - 1);
            int overlap = (overlapStart <= overlapEnd) ? countInRange(list, overlapStart, overlapEnd) : 0;
            c[i] = a[i] + b[i] - overlap;
        }

        // leftmost gap (gap 0)
        int leftCover0 = 0;
        if (!gapWalls[0].isEmpty()) {
            int firstPos = pos[0];
            int firstDist = dist[0];
            leftCover0 = countInRange(gapWalls[0], firstPos - firstDist, firstPos - 1);
        }

        //dp over robots
        final int NEG = -1_000_000_000;
        int[] dpPrev = new int[3];
        // robot 0
        int self0 = self[0];
        dpPrev[0] = 0;
        dpPrev[1] = leftCover0 + self0;
        dpPrev[2] = self0;

        for (int i = 1; i < n; i++) {
            int[] dpCurr = new int[3];
            int self_i = self[i];
            for (int cur = 0; cur < 3; cur++) {
                int best = NEG;
                int addSelf = (cur != 0) ? self_i : 0;
                for (int prev = 0; prev < 3; prev++) {
                    int gapContrib = 0;
                    if (prev == 2 && cur == 1) gapContrib = c[i];
                    else if (prev == 2) gapContrib = a[i];
                    else if (cur == 1) gapContrib = b[i];
                    int val = dpPrev[prev] + gapContrib;
                    if (val > best) best = val;
                }
                dpCurr[cur] = best + addSelf;
            }
            dpPrev = dpCurr;
        }

        // rightmost gap (gap n)
        int rightCoverLast = 0;
        if (!gapWalls[n].isEmpty()) {
            int lastPos = pos[n - 1];
            int lastDist = dist[n - 1];
            rightCoverLast = countInRange(gapWalls[n], lastPos + 1, lastPos + lastDist);
        }

        int ans = 0;
        for (int dir = 0; dir < 3; dir++) {
            int total = dpPrev[dir] + (dir == 2 ? rightCoverLast : 0);
            if (total > ans) ans = total;
        }
        return ans;
    }

    // count elements in sorted list that lie in [lo, hi] (inclusive)
    private int countInRange(List<Integer> list, int lo, int hi) {
        if (lo > hi) return 0;
        int left = Collections.binarySearch(list, lo);
        if (left < 0) left = -left - 1;
        int right = Collections.binarySearch(list, hi);
        if (right < 0) right = -right - 2;
        return (left <= right) ? (right - left + 1) : 0;
    }
}
