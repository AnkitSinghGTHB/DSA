class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        //find the fastest worker for a tight upper bound
        int minW = Integer.MAX_VALUE;
        for (int w : workerTimes) minW = Math.min(minW, w);
        //upper bound: time if the fastest worker does all the work
        long high = (long) minW * mountainHeight * (mountainHeight + 1L) / 2;
        long low = 0;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (canFinish(mid, mountainHeight, workerTimes)) {
                high = mid;
            } 
            else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean canFinish(long time, int target, int[] workerTimes) {
        long total = 0;
        for (int w : workerTimes) {
            total += maxHeight(w, time);
            if (total >= target) return true;
        }
        return total >= target;
    }

    // largest x such that w * x * (x + 1) / 2 <= time
    private long maxHeight(int w, long time) {
        long limit = (2 * time) / w;                // floor(2*time / w)
        // solve x*(x+1) <= limit  using quadratic formula
        double d = Math.sqrt(1 + 4.0 * limit);
        long x = (long) ((d - 1) / 2);
        // adjust if necessary (at most one step)
        if (x * (x + 1) > limit) {
            x--;
        } 
        else if ((x + 1) * (x + 2) <= limit) {
            x++;
        }
        return x;
    }
}
