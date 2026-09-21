//help from ds
class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k]; // dp[r] = number of subarrays ending at current index with product % k == r
        
        for (int x : nums) {
            long[] newDp = new long[k];
            int modX = x % k;
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newR = (r * modX) % k;
                    newDp[newR] += dp[r];
                }
            }
            newDp[modX] += 1;            
            for (int r = 0; r < k; r++) {
                result[r] += newDp[r];
            }
            
            dp = newDp;
        }
        
        return result;
    }
}
