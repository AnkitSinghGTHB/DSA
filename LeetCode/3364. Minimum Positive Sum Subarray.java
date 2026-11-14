class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();        
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums.get(i);
        }        
        int ans = Integer.MAX_VALUE;
        for (int start = 0; start < n; start++) {
            for (int len = l; len <= r && start + len <= n; len++) {
                int end = start + len;
                int sum = pref[end] - pref[start];                
                if (sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
