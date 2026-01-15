class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int horizontalBound = n + 2;
        int verticalBound = m + 2;
        int maxHorizontal = maxGap(hBars, horizontalBound);
        int maxVertical = maxGap(vBars, verticalBound);
        int side = Math.min(maxHorizontal, maxVertical);
        return side * side;
    }

    private int maxGap(int[] bars, int bound) {
        Arrays.sort(bars);
        int maxGap = 1; // at least one unit is always possible
        int i = 0;
        int len = bars.length;
        while (i < len) {
            int start = bars[i];
            int end = start;
            i++;
            while (i < len && bars[i] == end + 1) {
                end = bars[i];
                i++;
            }
            int gap;
            if (start == 1 && end == bound) {
                gap = bound - 1;
            } 
            else if (start == 1) {
                gap = end; // use bar at 1 and bar at end+1
            } 
            else if (end == bound) {
                gap = bound - start + 1;
            } 
            else {
                gap = end - start + 2;
            }
            maxGap = Math.max(maxGap, gap);
        }
        return maxGap;
    }
}
