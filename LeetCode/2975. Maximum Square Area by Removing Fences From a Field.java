class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        final long MOD = 1_000_000_007L;
        //build the set of x coordinates (vertical lines)
        List<Integer> xCoords = new ArrayList<>();
        xCoords.add(1);
        xCoords.add(m);
        for (int h : hFences) {
            xCoords.add(h);
        }
        Collections.sort(xCoords);
        //build the set of y coordinates (horizontal lines)
        List<Integer> yCoords = new ArrayList<>();
        yCoords.add(1);
        yCoords.add(n);
        for (int v : vFences) {
            yCoords.add(v);
        }
        Collections.sort(yCoords);
        //compute all possible horizontal side lengths (differences in xCoords)
        Set<Long> xDiffs = new HashSet<>();
        for (int i = 0; i < xCoords.size(); ++i) {
            for (int j = i + 1; j < xCoords.size(); ++j) {
                xDiffs.add((long) (xCoords.get(j) - xCoords.get(i)));
            }
        }
        //compute all possible vertical side lengths (differences in yCoords)
        Set<Long> yDiffs = new HashSet<>();
        for (int i = 0; i < yCoords.size(); ++i) {
            for (int j = i + 1; j < yCoords.size(); ++j) {
                yDiffs.add((long) (yCoords.get(j) - yCoords.get(i)));
            }
        }
        //find the maximum common side length
        long best = 0;
        for (long d : xDiffs) {
            if (yDiffs.contains(d) && d > best) {
                best = d;
            }
        }
        if (best == 0) {
            return -1;
        }
        long area = (best % MOD) * (best % MOD) % MOD;
        return (int) area;
    }
}
