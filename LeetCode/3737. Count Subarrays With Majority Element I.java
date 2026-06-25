class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == target) ? 1 : -1;
            prefix[i + 1] = prefix[i] + val;
        }
        int ans = 0;
        for (int l = 0; l < n; l++) {
            for (int r = l + 1; r <= n; r++) {
                if (prefix[r] - prefix[l] > 0) ans++;
            }
        }
        return ans;
    }
}
