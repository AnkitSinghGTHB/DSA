class Solution {
    public int sumCounts(List<Integer> nums) {
        //bruh
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(nums.get(j)); //this will store the seen
                int distinct = set.size();
                ans += distinct * distinct;
            }
        }
        return ans;
    }
}
