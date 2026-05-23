class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int dropCount = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                dropCount++;
            }
        }
        if (dropCount == 0) return true;
        if (dropCount == 1) return nums[0] >= nums[n - 1];
        return false;
    }
}
