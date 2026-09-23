class Solution {
    public int minOperations(int[] nums, int x) {
        //probably sliding window or backtracking
        //increasing window size till n
            //check window from left and from right
            //but then we are missing out on double ended pop operation
        int n = nums.length;
        int total = 0;
        for (int num : nums) total += num;

        int target = total - x;
        if (target < 0) return -1;
        if (target == 0) return n; // remove all elements

        // Find the longest subarray with sum == target
        int left = 0, sum = 0, maxLen = -1;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }
            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : n - maxLen;
    }
}
