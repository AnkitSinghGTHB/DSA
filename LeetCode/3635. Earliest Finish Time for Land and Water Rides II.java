//AI generated soln, cuz i didnt have enough time to do it
class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int landFirst = minFinishOrder(landStartTime, landDuration, waterStartTime, waterDuration);
        int waterFirst = minFinishOrder(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(landFirst, waterFirst);
    }

    private int minFinishOrder(int[] start1, int[] dur1, int[] start2, int[] dur2) {
        int n = start1.length;
        int m = start2.length;
        // sort the second category rides by start time
        int[][] second = new int[m][2];
        for (int i = 0; i < m; i++) {
            second[i][0] = start2[i];
            second[i][1] = dur2[i];
        }
        Arrays.sort(second, (a, b) -> a[0] - b[0]);

        // prefix minimum of duration for second category
        int[] prefMinDur = new int[m];
        prefMinDur[0] = second[0][1];
        for (int i = 1; i < m; i++) {
            prefMinDur[i] = Math.min(prefMinDur[i - 1], second[i][1]);
        }

        // suffix minimum of (start + duration) for second category
        int[] suffMinSum = new int[m + 1];
        suffMinSum[m] = Integer.MAX_VALUE;
        for (int i = m - 1; i >= 0; i--) {
            int sum = second[i][0] + second[i][1];
            suffMinSum[i] = Math.min(sum, suffMinSum[i + 1]);
        }

        int best = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int finishFirst = start1[i] + dur1[i];
            // binary search for the last ride in second with start <= finishFirst
            int lo = 0, hi = m - 1, idx = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (second[mid][0] <= finishFirst) {
                    idx = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            int candidate = Integer.MAX_VALUE;
            if (idx >= 0) {
                candidate = finishFirst + prefMinDur[idx];
            }
            if (idx + 1 < m) {
                candidate = Math.min(candidate, suffMinSum[idx + 1]);
            }
            best = Math.min(best, candidate);
        }
        return best;
    }
}
