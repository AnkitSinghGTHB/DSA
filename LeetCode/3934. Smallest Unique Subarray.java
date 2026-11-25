class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        //what even is rolling hash T-T
        int n = nums.length;
        int lo = 1, hi = n;
        int ans = n;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;

        final long MOD1 = 1_000_000_007L;
        final long MOD2 = 1_000_000_009L;
        final long BASE = 911382323L;

        // pow = BASE^(len - 1)  <-- fixed
        long pow1 = 1, pow2 = 1;
        for (int i = 0; i < len - 1; i++) {
            pow1 = pow1 * BASE % MOD1;
            pow2 = pow2 * BASE % MOD2;
        }

        long h1 = 0, h2 = 0;
        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE + nums[i]) % MOD1;
            h2 = (h2 * BASE + nums[i]) % MOD2;
        }

        Map<Long, Integer> count = new HashMap<>();
        long key = h1 * MOD2 + h2;
        count.put(key, 1);

        for (int i = len; i < n; i++) {
            // remove nums[i - len] * BASE^(len - 1)
            h1 = (h1 - nums[i - len] * pow1 % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE + nums[i]) % MOD1;

            h2 = (h2 - nums[i - len] * pow2 % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE + nums[i]) % MOD2;

            key = h1 * MOD2 + h2;
            count.put(key, count.getOrDefault(key, 0) + 1);
        }

        for (int c : count.values()) {
            if (c == 1)
                return true;
        }
        return false;
    }
}v
