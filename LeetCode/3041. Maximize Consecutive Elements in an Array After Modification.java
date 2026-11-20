//i took help from deepseek
class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        // dp[v] = maximum length of a consecutive sequence ending at value v
        int[] dp = new int[maxVal + 2];
        int ans = 0;

        for (int x : nums) {
            // Use current element as x (extend a sequence ending at x-1)
            int useAsX = Math.max(dp[x], dp[x - 1] + 1);

            // Use current element as x+1 (extend a sequence ending at x)
            int useAsXPlus1 = Math.max(dp[x + 1], dp[x] + 1);

            dp[x] = useAsX;
            dp[x + 1] = useAsXPlus1;

            ans = Math.max(ans, Math.max(useAsX, useAsXPlus1));
        }

        return ans;
    }
}
