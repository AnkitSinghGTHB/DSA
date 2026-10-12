import java.util.*;
//i took help from deepseek
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] count = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if ((x & (1 << i)) != 0) {
                    count[i]++;
                }
            }
        }
        
        long MOD = 1_000_000_007;
        long ans = 0;
        
        // For each of the k largest numbers, compute its maximum possible value.
        // The j-th largest number (1-indexed) can have bit i set if count[i] >= j.
        for (int j = 1; j <= k; j++) {
            long val = 0;
            for (int i = 0; i < 32; i++) {
                if (count[i] >= j) {
                    val += (1L << i);
                }
            }
            val %= MOD;
            ans = (ans + val * val) % MOD;
        }
        
        return (int) ans;
    }
}
