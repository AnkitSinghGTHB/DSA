class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        long[] pref = new long[n];
        int maxSoFar = 0;
        for (int i = 0; i < n; i++) {
            maxSoFar = Math.max(maxSoFar, nums[i]);
            pref[i] = gcd(nums[i], maxSoFar);
        }
        Arrays.sort(pref);
        long ans = 0;
        int left = 0, right = n - 1;
        while (left < right) {
            ans += gcd((int)pref[left], (int)pref[right]);
            left++;
            right--;
        }
        return ans;
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
