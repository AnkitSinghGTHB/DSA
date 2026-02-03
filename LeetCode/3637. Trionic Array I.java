class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        //need at least 4 indices to have valid p and q (0 < p < q < n-1)
        if (n < 4) {
            return false;
        }
        //bruting all possible p and q
        //there are 3 cases: 0 to p, p to q, q to n-1
        for (int p = 1; p <= n - 3; p++) {
            for (int q = p + 1; q <= n - 2; q++) {
                boolean valid = true;
                //check strictly increasing from 0 to p
                for (int i = 0; i < p; i++) {
                    if (nums[i] >= nums[i + 1]) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) continue;
                //check strictly decreasing from p to q
                for (int i = p; i < q; i++) {
                    if (nums[i] <= nums[i + 1]) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) continue;
                //check strictly increasing from q to n-1
                for (int i = q; i < n - 1; i++) {
                    if (nums[i] >= nums[i + 1]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    return true;
                }
            }
        }
        return false;
    }
}
