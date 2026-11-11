class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long ans = Long.MAX_VALUE;

        // The optimal number of type-3 items is at one of these breakpoints.
        long[] candidates = {
            0,
            need1,
            need2,
            Math.max(need1, need2)
        };

        for (long both : candidates) {
            long remaining1 = Math.max(0L, (long) need1 - both);
            long remaining2 = Math.max(0L, (long) need2 - both);

            long cost = both * costBoth
                      + remaining1 * cost1
                      + remaining2 * cost2;

            ans = Math.min(ans, cost);
        }

        return ans;
    }
}
