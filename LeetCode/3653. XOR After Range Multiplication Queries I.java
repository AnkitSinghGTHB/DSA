class Solution {
    private static final int MOD = 1_000_000_007;
    public int xorAfterQueries(int[] nums, int[][] queries) {
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            for (int i = l; i <= r; i += k) {
                nums[i] = (int) ((long) nums[i] * v % MOD);
            }
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }
}
