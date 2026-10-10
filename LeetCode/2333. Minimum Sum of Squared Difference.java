import java.util.Arrays;
//took help from deepseek
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = Math.abs(nums1[i] - nums2[i]);
        }
        // Sort ascending, then reverse to descending
        Arrays.sort(d);
        for (int i = 0; i < n / 2; i++) {
            int tmp = d[i];
            d[i] = d[n - 1 - i];
            d[n - 1 - i] = tmp;
        }

        long K = (long) k1 + k2; // total available reductions
        int i = 0;

        while (i < n && K > 0) {
            int nextVal = (i + 1 < n) ? d[i + 1] : 0;
            long cost = (long) (i + 1) * (d[i] - nextVal);

            if (K >= cost) {
                K -= cost;
                i++;
            } else {
                // Cannot reduce the whole group to nextVal.
                long fullSteps = K / (i + 1);
                int rem = (int) (K % (i + 1));
                long base = d[i] - fullSteps;

                long sum = 0;
                for (int j = 0; j <= i; j++) {
                    long val = base - (j < rem ? 1 : 0);
                    sum += val * val;
                }
                for (int j = i + 1; j < n; j++) {
                    sum += (long) d[j] * d[j];
                }
                return sum;
            }
        }

        if (i == n) {
            return 0; // all differences reduced to 0
        }

        // K == 0 exactly after reducing the first i+1 elements to d[i]
        long sum = (long) (i + 1) * d[i] * d[i];
        for (int j = i + 1; j < n; j++) {
            sum += (long) d[j] * d[j];
        }
        return sum;
    }
}
