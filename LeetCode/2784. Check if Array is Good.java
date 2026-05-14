class Solution {
    public boolean isGood(int[] nums) {
        int n = 0;
        for (int num : nums) {
            n = Math.max(n, num);
        }
        if (nums.length != n + 1) return false;
        int[] count = new int[n + 1];
        for (int num : nums) {
            if (num > n) return false;
            count[num]++;
        }
        if (count[n] != 2) return false;
        for (int i = 1; i < n; i++) {
            if (count[i] != 1) return false;
        }
        return true;
    }
}
