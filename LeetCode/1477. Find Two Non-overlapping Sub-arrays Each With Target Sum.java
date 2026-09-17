class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        final int INF = Integer.MAX_VALUE / 2;
        int[] best = new int[n];          // best[i] = min length of a target-sum subarray in arr[0..i]
        Arrays.fill(best, INF);

        int left = 0;
        int sum = 0;
        int ans = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // shrink window while sum > target
            while (sum > target && left <= right) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int len = right - left + 1;

                // try to pair with a non-overlapping subarray that ends before 'left'
                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, best[left - 1] + len);
                }

                // update best for current right
                if (right > 0) {
                    best[right] = Math.min(best[right - 1], len);
                } else {
                    best[right] = len;
                }
            } else {
                // no target-sum subarray ends at 'right'
                if (right > 0) {
                    best[right] = best[right - 1];
                }
            }
        }

        return ans == INF ? -1 : ans;
    }
}
