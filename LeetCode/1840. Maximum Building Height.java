class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        // Add virtual restrictions: building 1 must be height 0, and building n has no upper bound
        List<long[]> list = new ArrayList<>();
        list.add(new long[]{1, 0});
        // add (n, INF) as virtual restriction
        list.add(new long[]{n, Long.MAX_VALUE / 4});
        for (int[] r : restrictions) {
            list.add(new long[]{r[0], r[1]});
        }
        // sort by index
        Collections.sort(list, (a, b) -> Long.compare(a[0], b[0]));
        int m = list.size();

        // left-to-right pass: cap heights based on previous constraint
        for (int i = 1; i < m; i++) {
            long dist = list.get(i)[0] - list.get(i - 1)[0];
            long possible = list.get(i - 1)[1] + dist;
            if (possible < list.get(i)[1]) {
                list.get(i)[1] = possible;
            }
        }

        // right-to-left pass: cap heights based on next constraint
        for (int i = m - 2; i >= 0; i--) {
            long dist = list.get(i + 1)[0] - list.get(i)[0];
            long possible = list.get(i + 1)[1] + dist;
            if (possible < list.get(i)[1]) {
                list.get(i)[1] = possible;
            }
        }

        long ans = 0;
        for (long[] p : list) {
            ans = Math.max(ans, p[1]);
        }

        // maximum between consecutive restrictions
        for (int i = 0; i < m - 1; i++) {
            long idx1 = list.get(i)[0], h1 = list.get(i)[1];
            long idx2 = list.get(i + 1)[0], h2 = list.get(i + 1)[1];
            long dist = idx2 - idx1;
            long maxBetween = (h1 + h2 + dist) / 2;
            ans = Math.max(ans, maxBetween);
        }

        return (int) ans;
    }
}
