class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long NEG = Long.MIN_VALUE / 2;
        long[] dp0 = new long[n]; // increasing (length >= 2)
        long[] dp1 = new long[n]; // inc then dec (length >= 4)
        long[] dp2 = new long[n]; // inc, dec, inc (trionic, length >= 4)
        dp0[0] = NEG;
        dp1[0] = NEG;
        dp2[0] = NEG;
        for (int i = 1; i < n; i++) {
            //state0: increasing subarray of length >= 2 ending at i
            if (nums[i] > nums[i - 1]) {
                long extend = (dp0[i - 1] == NEG) ? NEG : dp0[i - 1] + nums[i];
                long start = (long) nums[i - 1] + nums[i];
                dp0[i] = Math.max(extend, start);
            } 
            else {
                dp0[i] = NEG;
            }
            //state1: increasing then decreasing, ending at i
            if (nums[i] < nums[i - 1]) {
                long extend = (dp1[i - 1] == NEG) ? NEG : dp1[i - 1] + nums[i];
                long start = (dp0[i - 1] == NEG) ? NEG : dp0[i - 1] + nums[i];
                dp1[i] = Math.max(extend, start);
            } 
            else {
                dp1[i] = NEG;
            }
            //state2: increasing, decreasing, then increasing, ending at i (trionic)
            if (nums[i] > nums[i - 1]) {
                long extend = (dp2[i - 1] == NEG) ? NEG : dp2[i - 1] + nums[i];
                long start = (dp1[i - 1] == NEG) ? NEG : dp1[i - 1] + nums[i];
                dp2[i] = Math.max(extend, start);
            } 
            else {
                dp2[i] = NEG;
            }
        }
        long ans = NEG;
        for (int i = 0; i < n; i++) {
            if (dp2[i] > ans) {
                ans = dp2[i];
            }
        }
        return ans;
    }
}
