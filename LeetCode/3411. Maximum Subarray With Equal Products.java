class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;
        // bro worked on the initial soln i gave to fix
        // For numbers <= 10, lcm of any subset divides lcm(1..10)=2520.
        // gcd <= 10, so gcd*lcm <= 25200.
        // If product > 25200, the condition can never be true again
        // because all numbers are positive and product only grows.
        final int LIMIT = 25200;

        for (int left = 0; left < n; left++) {
            long product = 1;
            int g = 0;      // gcd identity: gcd(0, x) = x
            int l = 1;      // lcm identity: lcm(1, x) = x

            for (int right = left; right < n; right++) {
                product *= nums[right];

                if (product > LIMIT) {
                    break;
                }

                g = gcd(g, nums[right]);
                l = lcm(l, nums[right]);

                if (product == (long) g * l) {
                    ans = Math.max(ans, right - left + 1);
                }
            }
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

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
