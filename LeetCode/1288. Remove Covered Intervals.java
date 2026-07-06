import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start ascending; if tie, by end descending
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return b[1] - a[1];
        });
        
        int count = 0;
        int maxEnd = -1;
        for (int[] inter : intervals) {
            int l = inter[0], r = inter[1];
            if (r > maxEnd) {
                // This interval is not covered by any previous one
                count++;
                maxEnd = r;
            }
            // else it is covered, so we skip it
        }
        return count;
    }
}
