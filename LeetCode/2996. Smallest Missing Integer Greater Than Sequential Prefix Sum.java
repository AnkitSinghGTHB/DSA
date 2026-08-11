class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int i;
        for (i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1] + 1) break;
        }
        int sum = 0;
        for (int j = 0; j < i; j++) {
            sum += nums[j];
        }
        Set<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        int candidate = sum;
        while (set.contains(candidate)) {
            candidate++;
        }
        return candidate;
    }
}
