import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int v : nums) {
            freq.put((long) v, freq.getOrDefault((long) v, 0) + 1);
        }
        
        int ans = 0;
        for (long x : freq.keySet()) {
            if (x == 1) {
                // we can take the largest odd number of 1's
                int cnt = freq.get(1L);
                int bestOdd = (cnt % 2 == 0) ? cnt - 1 : cnt;
                if (bestOdd > ans) ans = bestOdd;
                continue;
            }
            
            // build chain: x, x^2, x^4, ... <= 1e9
            List<Long> chain = new ArrayList<>();
            long cur = x;
            while (cur <= 1_000_000_000L) {
                chain.add(cur);
                if (cur > 1_000_000_000L / cur) break;
                cur = cur * cur;
            }
            
            // try all possible m = index of the center element
            for (int m = 0; m < chain.size(); m++) {
                boolean ok = true;
                // for every level below m we need at least two copies
                for (int j = 0; j < m; j++) {
                    if (freq.getOrDefault(chain.get(j), 0) < 2) {
                        ok = false;
                        break;
                    }
                }
                if (!ok) break; // larger m also impossible
                
                // need at least one copy of the center element
                if (freq.getOrDefault(chain.get(m), 0) >= 1) {
                    int len = 2 * m + 1;
                    if (len > ans) ans = len;
                } else {
                    break; // center missing, larger m won't help
                }
            }
        }
        return ans;
    }
}
