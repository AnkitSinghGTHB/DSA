//i took help from Deepseek
class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // Sort coins (not necessary but ensures smallest first)
        Arrays.sort(coins);
        int m = coins.length;
        long hi = (long) coins[0] * k; // upper bound

        int totalMasks = 1 << m;
        long[] lcms = new long[totalMasks];
        int[] pop = new int[totalMasks];
        
        // Precompute lcm for every subset, capped at hi+1
        for (int mask = 1; mask < totalMasks; mask++) {
            int lsb = mask & -mask;
            int idx = Integer.numberOfTrailingZeros(lsb);
            int prev = mask ^ lsb;
            long prevLcm = (prev == 0) ? 1 : lcms[prev];
            long cur = coins[idx];
            long g = gcd(prevLcm, cur);
            long l = prevLcm / g * cur;  // may overflow, but we guard
            if (l > hi) l = hi + 1;
            lcms[mask] = l;
            pop[mask] = pop[prev] + 1;
        }

        // Binary search the smallest x with count >= k
        long lo = 1;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (count(mid, lcms, pop, hi) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private long count(long x, long[] lcms, int[] pop, long cap) {
        long total = 0;
        for (int mask = 1; mask < lcms.length; mask++) {
            long l = lcms[mask];
            if (l > x) continue;
            long add = x / l;
            if ((pop[mask] & 1) == 1) total += add;
            else total -= add;
        }
        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
