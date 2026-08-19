class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i <= n - k; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i; j < i + k; j++) {
                seen.add(nums[j]);
            }
            for (int val : seen) {
                count.put(val, count.getOrDefault(val, 0) + 1);
            }
        }
        int ans = -1;
        for (int val : count.keySet()) {
            if (count.get(val) == 1) {
                ans = Math.max(ans, val);
            }
        }
        return ans;
    }
}
