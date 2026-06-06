class Solution {
    public int[] leftRightDifference(int[] nums) {
        /*10,4,8,3
        sum = 10+4+8+3 = 25
        pre = 0 (at start)
        0th :
                l = pre = 0
                r = sum - pre - num[0] = 25 - 0 - 10 = 15
           ans[0] = abs(l-r) = |0 - 15| = 15
              pre = pre + num[0] = 0 + 10 = 10
        1th :
                l = pre = 10
                r = sum - pre - num[1] = 25 - 10 - 4 = 11
           ans[1] = abs(l-r) = |10 - 11| = 1
              pre = pre + num[1] = 10 + 4 = 14
        2nd : so on ...
        */
        int n = nums.length;
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int[] ans = new int[n];
        int prefix = 0;
        for (int i = 0; i < n; i++) {
            int left = prefix;
            int right = total - prefix - nums[i];
            ans[i] = Math.abs(left - right);
            prefix += nums[i];
        }
        return ans;
    }
}
