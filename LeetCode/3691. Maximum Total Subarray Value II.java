//solved using AI bcoz my logic was expensive
class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        // Precompute logarithms for RMQ
        int[] log = new int[n + 1];
        log[1] = 0;
        for (int i = 2; i <= n; i++) log[i] = log[i / 2] + 1;
        
        int K = log[n] + 1;
        int[][] stMax = new int[K][n];
        int[][] stMin = new int[K][n];
        for (int i = 0; i < n; i++) {
            stMax[0][i] = nums[i];
            stMin[0][i] = nums[i];
        }
        for (int j = 1; j < K; j++) {
            int step = 1 << (j - 1);
            for (int i = 0; i + (1 << j) <= n; i++) {
                stMax[j][i] = Math.max(stMax[j-1][i], stMax[j-1][i+step]);
                stMin[j][i] = Math.min(stMin[j-1][i], stMin[j-1][i+step]);
            }
        }
        
        // Helper to get range max/min
        java.util.function.BiFunction<Integer,Integer,Integer> rangeMax = (l, r) -> {
            int len = r - l + 1;
            int j = log[len];
            return Math.max(stMax[j][l], stMax[j][r - (1 << j) + 1]);
        };
        java.util.function.BiFunction<Integer,Integer,Integer> rangeMin = (l, r) -> {
            int len = r - l + 1;
            int j = log[len];
            return Math.min(stMin[j][l], stMin[j][r - (1 << j) + 1]);
        };
        
        // Max‑heap: store (value, left, right)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int l = 0; l < n; l++) {
            int r = n - 1;
            int val = rangeMax.apply(l, r) - rangeMin.apply(l, r);
            pq.offer(new int[]{val, l, r});
        }
        
        long ans = 0;
        for (int i = 0; i < k; i++) {
            int[] top = pq.poll();
            ans += top[0];
            int l = top[1], r = top[2];
            if (r > l) {
                int newR = r - 1;
                int newVal = rangeMax.apply(l, newR) - rangeMin.apply(l, newR);
                pq.offer(new int[]{newVal, l, newR});
            }
        }
        return ans;
    }
}
