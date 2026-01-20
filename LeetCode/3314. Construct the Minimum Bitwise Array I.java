class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int p = nums.get(i);
            //for p = 2 there is no solution
            if (p == 2) {
                ans[i] = -1;
                continue;
            }
            //for odd primes, the optimal t = (exponent of 2 in (p+1)) - 1
            int k = Integer.numberOfTrailingZeros(p + 1);
            int t = k - 1; //k >= 1 for odd p, so t >= 0
            int x = p - (1 << t);
            ans[i] = x;
        }
        return ans;
    }
}
