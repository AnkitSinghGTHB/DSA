//this solutionis ai assisted since i wasnt able to do on my own

class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        int m = k - 2; // number of additional starts we need from the window
        
        // If m == 0, we just need to choose the second start (i1) with minimum value
        if (m == 0) {
            long minSecond = Long.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                minSecond = Math.min(minSecond, nums[i]);
            }
            return (long) nums[0] + minSecond;
        }
        
        // We need to iterate i1 from 1 to n-1-m (inclusive)
        // because the window [i1+1, i1+dist] must have at least m elements
        // condition: min(dist, n-1 - i1) >= m  =>  n-1 - i1 >= m  => i1 <= n-1 - m
        
        // Two TreeMaps to maintain the m smallest in the current window
        TreeMap<Integer, Integer> low = new TreeMap<>();   // m smallest (multiset)
        TreeMap<Integer, Integer> high = new TreeMap<>();  // the rest
        
        long[] sum = new long[]{0};          // sum of elements in low
        int[] lowSize = new int[]{0};        // number of elements in low
        
        // Initial window for i1 = 1: indices [2, min(1+dist, n-1)]
        int L = 2;
        int R = Math.min(1 + dist, n - 1);
        
        // Build initial window
        for (int i = L; i <= R; i++) {
            add(low, high, nums[i], m, sum, lowSize);
        }
        
        long ans = Long.MAX_VALUE;
        
        // Iterate i1 from 1 to n-1-m
        for (int i1 = 1; i1 <= n - 1 - m; i1++) {
            // Only consider if we have exactly m elements in low (window has at least m)
            if (lowSize[0] == m) {
                long total = (long) nums[0] + (long) nums[i1] + sum[0];
                ans = Math.min(ans, total);
            }
            
            // Slide window: remove leftmost element (index L)
            if (L <= n - 1) {
                remove(low, high, nums[L], m, sum, lowSize);
                L++;
            }
            // If possible, add new rightmost element
            if (R < n - 1) {
                R++;
                add(low, high, nums[R], m, sum, lowSize);
            }
        }
        
        return ans == Long.MAX_VALUE ? -1 : ans;
    }
    
    // Helper to add one occurrence of x
    private void addOne(TreeMap<Integer, Integer> map, int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
    }
    
    // Helper to remove one occurrence of x
    private void removeOne(TreeMap<Integer, Integer> map, int x) {
        int cnt = map.get(x);
        if (cnt == 1) {
            map.remove(x);
        } else {
            map.put(x, cnt - 1);
        }
    }
    
    private void add(TreeMap<Integer, Integer> low, TreeMap<Integer, Integer> high,
                     int x, int m, long[] sum, int[] lowSize) {
        if (lowSize[0] < m) {
            // low has capacity, add to low
            addOne(low, x);
            lowSize[0]++;
            sum[0] += x;
        } else {
            // low is full, compare with max in low
            int last = low.lastKey();
            if (x < last) {
                // move last from low to high, then add x to low
                removeOne(low, last);
                addOne(high, last);
                lowSize[0]--;
                sum[0] -= last;
                
                addOne(low, x);
                lowSize[0]++;
                sum[0] += x;
            } else {
                // x belongs to high
                addOne(high, x);
            }
        }
    }
    
    private void remove(TreeMap<Integer, Integer> low, TreeMap<Integer, Integer> high,
                        int x, int m, long[] sum, int[] lowSize) {
        if (low.containsKey(x)) {
            removeOne(low, x);
            lowSize[0]--;
            sum[0] -= x;
            
            // Now low may have size m-1, so we need to move the smallest from high to low
            if (!high.isEmpty()) {
                int firstHigh = high.firstKey();
                removeOne(high, firstHigh);
                addOne(low, firstHigh);
                lowSize[0]++;
                sum[0] += firstHigh;
            }
        } else if (high.containsKey(x)) {
            removeOne(high, x);
        }
    }
}
