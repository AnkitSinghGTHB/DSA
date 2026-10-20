import java.util.Arrays;
//deepseek
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        long ans = 0;

        // Enumerate all divisors of n
        for (int d = 1; d * d <= n; d++) {
            if (n % d != 0) continue;

            int k1 = d;
            if (isSortable(k1, nums, sorted)) {
                ans += k1;
            }

            int k2 = n / d;
            if (k2 != k1 && isSortable(k2, nums, sorted)) {
                ans += k2;
            }
        }

        return (int) ans;
    }

    private boolean isSortable(int k, int[] nums, int[] sorted) {
        int n = nums.length;

        // For k = 1, no rotation changes anything.
        if (k == 1) {
            for (int i = 0; i < n; i++) {
                if (nums[i] != sorted[i]) return false;
            }
            return true;
        }

        int[] pi = new int[k]; // reused for every block

        for (int start = 0; start < n; start += k) {
            if (!isRotation(start, k, nums, sorted, pi)) {
                return false;
            }
        }
        return true;
    }

    // Checks whether sorted[start .. start+k-1] is a cyclic rotation of
    // nums[start .. start+k-1].
    private boolean isRotation(int start, int k, int[] nums, int[] sorted, int[] pi) {
        // Build KMP prefix function for pattern sorted[start .. start+k-1]
        pi[0] = 0;
        for (int i = 1; i < k; i++) {
            int j = pi[i - 1];
            while (j > 0 && sorted[start + i] != sorted[start + j]) {
                j = pi[j - 1];
            }
            if (sorted[start + i] == sorted[start + j]) {
                j++;
            }
            pi[i] = j;
        }

        // Search pattern inside nums[start .. start+k-1] repeated twice
        int j = 0;
        for (int t = 0; t < 2 * k; t++) {
            int textChar = nums[start + (t % k)];

            while (j > 0 && textChar != sorted[start + j]) {
                j = pi[j - 1];
            }
            if (textChar == sorted[start + j]) {
                j++;
            }
            if (j == k) {
                return true;
            }
        }
        return false;
    }
}
