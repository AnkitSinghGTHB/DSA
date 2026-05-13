class Solution {
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int pairs = n / 2;
        int maxSum = 2 * limit;
        int[] diff = new int[maxSum + 3]; // extra space for right+1 index

        for (int i = 0; i < pairs; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            int low = Math.min(a, b);
            int high = Math.max(a, b);

            int left = low + 1;
            int right = high + limit;
            // interval [left, right] : cost decreases by 1 (from 2 to 1)
            diff[left]--;
            diff[right + 1]++;

            int sum = a + b;
            // at the exact sum point, cost decreases by another 1 (from 1 to 0)
            diff[sum]--;
            diff[sum + 1]++;
        }

        int curr = 0;
        int base = 2 * pairs;
        int ans = Integer.MAX_VALUE;
        for (int s = 2; s <= maxSum; s++) {
            curr += diff[s];
            int moves = base + curr;
            if (moves < ans) ans = moves;
        }
        return ans;
    }
}
