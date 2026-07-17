import java.util.*;
//deepseek solved it, couldnt form intuition at all
class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int n = nums.length;
        int maxVal = 0;
        for (int v : nums) if (v > maxVal) maxVal = v;
        
        // frequency of each number
        int[] freq = new int[maxVal + 1];
        for (int v : nums) freq[v]++;
        
        // count[x] = number of elements divisible by x
        long[] count = new long[maxVal + 1];
        for (int x = 1; x <= maxVal; x++) {
            long sum = 0;
            for (int m = x; m <= maxVal; m += x) {
                sum += freq[m];
            }
            count[x] = sum;
        }
        
        // F[x] = number of pairs with GCD exactly x
        long[] F = new long[maxVal + 1];
        for (int x = maxVal; x >= 1; x--) {
            long total = count[x] * (count[x] - 1) / 2; // C(count[x], 2)
            for (int k = 2; k * x <= maxVal; k++) {
                total -= F[k * x];
            }
            F[x] = total;
        }
        
        // answer queries offline
        int q = queries.length;
        Integer[] order = new Integer[q];
        for (int i = 0; i < q; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Long.compare(queries[a], queries[b]));
        
        int[] ans = new int[q];
        long cumulative = 0;
        int ptr = 0;
        for (int x = 1; x <= maxVal && ptr < q; x++) {
            cumulative += F[x];
            while (ptr < q && cumulative > queries[order[ptr]]) {
                ans[order[ptr]] = x;
                ptr++;
            }
        }
        return ans;
    }
}
