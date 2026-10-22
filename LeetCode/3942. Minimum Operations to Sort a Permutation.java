import java.util.*;
//deepseek
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // target0[k] = true if rotating nums left by k gives sorted array
        // target1[k] = true if rotating reversed(nums) left by k gives sorted array
        boolean[] target0 = new boolean[n];
        boolean[] target1 = new boolean[n];

        int k0 = rotationToSorted(nums, n);
        if (k0 != -1) target0[k0] = true;

        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            rev[i] = nums[n - 1 - i];
        }
        int k1 = rotationToSorted(rev, n);
        if (k1 != -1) target1[k1] = true;

        // State encoding:
        // state = o * n + k
        // o = 0 -> current array is nums rotated left by k
        // o = 1 -> current array is reversed(nums) rotated left by k
        int totalStates = 2 * n;
        int[] dist = new int[totalStates];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        dist[0] = 0; // start: nums, no rotation, not reversed
        queue.add(0);

        while (!queue.isEmpty()) {
            int state = queue.poll();
            int o = state / n;
            int k = state % n;

            if ((o == 0 && target0[k]) || (o == 1 && target1[k])) {
                return dist[state];
            }

            // Operation 1: rotate left by one
            int nextL = o * n + (k + 1) % n;
            if (dist[nextL] == -1) {
                dist[nextL] = dist[state] + 1;
                queue.add(nextL);
            }

            // Operation 2: reverse the whole array
            int nextK = (n - k) % n;
            int nextR = (1 - o) * n + nextK;
            if (dist[nextR] == -1) {
                dist[nextR] = dist[state] + 1;
                queue.add(nextR);
            }
        }

        return -1;
    }

    // Returns k such that rotating arr left by k gives [0, 1, ..., n-1],
    // or -1 if no such k exists.
    private int rotationToSorted(int[] arr, int n) {
        int k = -1;
        for (int j = 0; j < n; j++) {
            int cur = (j - arr[j]) % n;
            if (cur < 0) cur += n;

            if (j == 0) {
                k = cur;
            } else if (cur != k) {
                return -1;
            }
        }
        return k;
    }
}
