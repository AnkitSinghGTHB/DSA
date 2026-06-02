class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinish = Integer.MAX_VALUE;
        int n = landStartTime.length;
        int m = waterStartTime.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Land first, then water
                int finishLand = landStartTime[i] + landDuration[i];
                int startWater = Math.max(finishLand, waterStartTime[j]);
                int finishWater = startWater + waterDuration[j];
                int total1 = finishWater;
                // Water first, then land
                int finishWater2 = waterStartTime[j] + waterDuration[j];
                int startLand = Math.max(finishWater2, landStartTime[i]);
                int finishLand2 = startLand + landDuration[i];
                int total2 = finishLand2;
                int best = Math.min(total1, total2);
                if (best < minFinish) minFinish = best;
            }
        }
        return minFinish;
    }
}
