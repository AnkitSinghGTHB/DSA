class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        boolean sorted = true;
        int and = -1; // all bits set initially

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                sorted = false;
                if (and == -1) {
                    and = nums[i];
                } else {
                    and &= nums[i];
                }
            }
        }

        if (sorted) return 0;
        return and;
    }
}
