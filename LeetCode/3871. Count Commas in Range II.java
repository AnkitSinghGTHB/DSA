class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long pow = 1000L; // 10^3
        while (pow <= n) {
            ans += n - pow + 1;
            // next power of 1000 (10^3)
            if (pow > Long.MAX_VALUE / 1000) break;
            pow *= 1000;
        }
        return ans;
    }
}
